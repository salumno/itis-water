package ru.kpfu.itis.water.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000)
    private String text;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @OneToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ticket")
    private List<TicketMessage> messages;
}
