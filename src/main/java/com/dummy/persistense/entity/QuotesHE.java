package com.dummy.persistense.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quotes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuotesHE implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private int id;
    @Column(name = "QUOTE", nullable = false)
    private String quote;
    @Column(name = "AUTHOR", nullable = false)
    private String author;
}
