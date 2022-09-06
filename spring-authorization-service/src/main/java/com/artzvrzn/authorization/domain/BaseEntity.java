package com.artzvrzn.authorization.domain;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class BaseEntity {
  @Column(columnDefinition = "timestamp(3)")
  private LocalDateTime created;
  @Version
  @Column(columnDefinition = "timestamp(3)")
  private LocalDateTime updated;

  @PrePersist
  void onInsert() {
    this.created = LocalDateTime.now(ZoneOffset.UTC);
    this.updated = this.created;
  }

  @PreUpdate
  void onUpdate() {
    this.updated = LocalDateTime.now(ZoneOffset.UTC);
  }
}
