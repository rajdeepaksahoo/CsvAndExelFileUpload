package com.csvandexel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CsvEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "period")
    private String period;
    @Column(name = "series_reference")
    private String seriesReference;
    @Column(name = "region_name")
    private String regionName;
    @Column(name = "filled jobs")
    private String filledJobs;
}
