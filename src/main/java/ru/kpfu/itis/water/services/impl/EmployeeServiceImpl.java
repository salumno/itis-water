package ru.kpfu.itis.water.services.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.water.dto.EmployeeDto;
import ru.kpfu.itis.water.form.EmployeeAddForm;
import ru.kpfu.itis.water.form.EmployeeUpdateForm;
import ru.kpfu.itis.water.model.*;
import ru.kpfu.itis.water.repositories.DepartmentRepository;
import ru.kpfu.itis.water.repositories.EmployeeRepository;
import ru.kpfu.itis.water.security.roles.UserRole;
import ru.kpfu.itis.water.security.status.UserStatus;
import ru.kpfu.itis.water.services.EmployeeService;
import ru.kpfu.itis.water.util.EmailSender;
import ru.kpfu.itis.water.util.LoginPasswordGenerator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final String FILTER_UNDEFINED_VALUE = "undefined";

    private EmployeeRepository employeeRepository;
    private EmailSender emailSender;
    private PasswordEncoder passwordEncoder;
    private DepartmentRepository departmentRepository;
    private LoginPasswordGenerator loginPasswordGenerator;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmailSender emailSender, PasswordEncoder passwordEncoder, DepartmentRepository departmentRepository, LoginPasswordGenerator loginPasswordGenerator) {
        this.employeeRepository = employeeRepository;
        this.emailSender = emailSender;
        this.passwordEncoder = passwordEncoder;
        this.departmentRepository = departmentRepository;
        this.loginPasswordGenerator = loginPasswordGenerator;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findOneById(id).orElseThrow(
                () -> new IllegalArgumentException("Employee with id: " + id + " not found.")
        );
    }

    @Override
    public void addNewEmployee(EmployeeAddForm form) {
        LoginPassword loginPassword = loginPasswordGenerator.generate();
        User user = createUserByEmployeeForm(form);
        UserData userData = createUserDataByEmployeeForm(form, user, loginPassword);
        Employee employee = createEmployee(form, userData);
        employeeRepository.save(employee);
        sendCredentialsToEmployee(loginPassword, employee);
    }

    @Override
    public List<EmployeeDto> getAllDTOEmployees() {
        return EmployeeDto.from(getAllEmployees());
    }

    @Override
    public void updateEmployee(Long employeeId, EmployeeUpdateForm form) {
        Employee employee = getEmployeeById(employeeId);
        updateCurrentEmployee(employee, form);
        updateCurrentEmployeeUserData(employee.getUserData(), form);
        employeeRepository.save(employee);
    }

    @Override
    public UserStatus[] getEmployeeStatuses() {
        return UserStatus.values();
    }

    @Override
    public UserRole[] getEmployeeRoles() {
        return UserRole.values();
    }

    @Override
    public EmployeeDto getEmployeeDTOById(Long employeeId) {
        return EmployeeDto.from(getEmployeeById(employeeId));
    }

    @Override
    public List<EmployeeDto> getEmployeeDTOByNameFilterValue(String rawFilterValue) {
        if (FILTER_UNDEFINED_VALUE.equals(rawFilterValue)) {
            return getAllDTOEmployees();
        }
        String filterName = rawFilterValue.trim().toLowerCase();
        return getAllDTOEmployees().stream()
                .filter(e -> {
                    String rawFullName = e.getSurname() + ' ' + e.getName() + ' ' + e.getPatro();
                    String fullName = rawFullName.toLowerCase();
                    return fullName.contains(filterName);
                })
                .collect(Collectors.toList());
    }

    private User createUserByEmployeeForm(EmployeeAddForm form) {
        UserRole userRole = UserRole.valueOf(form.getRole());
        return User.builder()
                .name(form.getName())
                .surname(form.getSurname())
                .role(userRole)
                .build();
    }

    private UserData createUserDataByEmployeeForm(EmployeeAddForm form, User user, LoginPassword loginPassword) {
        UserRole userRole = UserRole.valueOf(form.getRole());
        return UserData.builder()
                .login(loginPassword.getLogin())
                .hashPassword(passwordEncoder.encode(loginPassword.getPassword()))
                .userRole(userRole)
                .status(UserStatus.CONFIRMED)
                .user(user)
                .build();
    }

    private Employee createEmployee(EmployeeAddForm form, UserData userData) {
        Department department = departmentRepository.findOneById(form.getDepartmentId()).orElseThrow(
                () -> new IllegalArgumentException("Department with id: " + form.getDepartmentId() + " not found.")
        );
        return Employee.builder()
                .surname(form.getSurname())
                .name(form.getName())
                .patro(form.getPatro())
                .email(form.getEmail())
                .inn(form.getInn())
                .department(department)
                .salary(form.getSalary())
                .comment(form.getComment())
                .userData(userData).build();
    }

    private void sendCredentialsToEmployee(LoginPassword loginPassword, Employee employee) {
        emailSender.sendEmailMessage(
                employee.getEmail(),
                "Водоканал мистера Ланштейна. Входные данные",
                getCredsMessage(loginPassword, employee)
        );
    }

    private String getCredsMessage(LoginPassword loginPassword, Employee employee) {
        return "<h2>Здравствуйте," + employee.getName() + " " + employee.getPatro() + "!</h2>" +
                "<h3>Вы были успешно добавлены в систему \"Водоканал мистера Ланштейна\".</h3>" +
                "<h3>Высылаем Вам данные для входа в систему:</h3>" +
                "<h3>Логин: " + loginPassword.getLogin() + "</h3>" +
                "<h3>Пароль: " + loginPassword.getPassword() + "</h3>";
    }

    private void updateCurrentEmployee(Employee currentEmployee, EmployeeUpdateForm form) {
        Department department = departmentRepository.findOneById(form.getDepartmentId()).orElseThrow(
                () -> new IllegalArgumentException("Department with id: " + form.getDepartmentId() + " not found.")
        );
        currentEmployee.setSurname(form.getSurname());
        currentEmployee.setName(form.getName());
        currentEmployee.setPatro(form.getPatro());
        currentEmployee.setEmail(form.getEmail());
        currentEmployee.setInn(form.getInn());
        currentEmployee.setSalary(form.getSalary());
        currentEmployee.setDepartment(department);
        currentEmployee.setComment(form.getComment());
    }

    private void updateCurrentEmployeeUserData(UserData currentEmployeeUserData, EmployeeUpdateForm form) {
        UserStatus newEmployeeStatus = UserStatus.valueOf(form.getStatus());
        UserRole newEmployeeUserRole = UserRole.valueOf(form.getRole());
        currentEmployeeUserData.setStatus(newEmployeeStatus);
        currentEmployeeUserData.setUserRole(newEmployeeUserRole);
        currentEmployeeUserData.getUser().setRole(newEmployeeUserRole);
    }

}
