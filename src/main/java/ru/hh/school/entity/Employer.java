package ru.hh.school.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//TODO: оформите entity
@Entity
public class Employer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "employer_id")
  private Integer id;
  @Column(name = "company_name")
  private String companyName;

  // не используйте java.util.Date
  // https://docs.jboss.org/hibernate/orm/5.3/userguide/html_single/Hibernate_User_Guide.html#basic-datetime-java8
  @Column(name = "creation_time")
  private LocalDateTime creationTime;
  @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Vacancy> vacancies = new ArrayList<>();
  @Column(name = "block_time")
  private LocalDateTime blockTime;

  public Employer() {}

  public List<Vacancy> getVacancies() {
    return vacancies;
  }

  public Integer getId() {
    return id;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public LocalDateTime getBlockTime() {
    return blockTime;
  }

  public void setBlockTime(LocalDateTime blockTime) {
    this.blockTime = blockTime;
  }

  // статьи на тему реализации equals() и hashCode():
  //
  // https://vladmihalcea.com/hibernate-facts-equals-and-hashcode/
  // https://docs.jboss.org/hibernate/orm/5.3/userguide/html_single/Hibernate_User_Guide.html#mapping-model-pojo-equalshashcode
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Employer)) return false;
    Employer employer = (Employer) o;
    if (!companyName.equals(employer.companyName)) return false;
    return Objects.equals(creationTime, employer.creationTime);
  }

  @Override
  public int hashCode() {
    int result = companyName.hashCode();
    result = 31 * result + (creationTime != null ? creationTime.hashCode() : 0);
    return result;
  }
}
