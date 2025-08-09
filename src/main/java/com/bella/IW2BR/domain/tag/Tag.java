package com.bella.IW2BR.domain.tag;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.environment.util.EnvironmentMember;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "tags")
@Data
@NoArgsConstructor
public class Tag implements EnvironmentMember {
  @Builder
  public Tag(
      String title,
      String description,
      int positiveFlaggedCount,
      int negativeFlaggedCount,
      LocalDate lastSeen,
      Environment environment) {
    this.title = title;
    this.description = description;
    this.positiveFlaggedCount = positiveFlaggedCount;
    this.negativeFlaggedCount = negativeFlaggedCount;
    this.environment = environment;
  }

  @Id @GeneratedValue private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description", nullable = true)
  private String description;

  @Column(name = "positive_flagged_count", nullable = false)
  private int positiveFlaggedCount;

  @Column(name = "negative_flagged_count", nullable = false)
  private int negativeFlaggedCount;

  @ManyToOne
  @JoinColumn(name = "environment_id", nullable = false)
  private Environment environment;

  public void flagPositive() {
    this.setPositiveFlaggedCount(getPositiveFlaggedCount() + 1);
  }

  public void flagNegative() {
    this.setNegativeFlaggedCount(getNegativeFlaggedCount() + 1);
  }
}
