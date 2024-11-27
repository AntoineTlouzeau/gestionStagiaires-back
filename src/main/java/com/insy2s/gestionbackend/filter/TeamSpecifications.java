package com.insy2s.gestionbackend.filter;

import com.insy2s.gestionbackend.model.Manager;
import com.insy2s.gestionbackend.model.Skill;
import com.insy2s.gestionbackend.model.Team;
import com.insy2s.gestionbackend.repository.SkillRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class TeamSpecifications {
    private final SkillRepository skillRepository;
    private String lastName;

    public TeamSpecifications(SkillRepository skillRepository, String lastName) {
        this.skillRepository = skillRepository;
        this.lastName = lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Specification<Team> hasIsWeekEven(Boolean isWeekEven) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isWeekEven"), isWeekEven);
    }

    public Specification<Team> hasNameContaining(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public Specification<Team> hasSkillId(String skillId) {
        return (root, query, criteriaBuilder) -> {
            if (skillId != null && !skillId.isEmpty()) {
                Skill skill = skillRepository.findById(skillId).orElse(null);
                if (skill != null) {
                    return criteriaBuilder.isMember(skill, root.get("skills"));
                }
            }
            return criteriaBuilder.conjunction();
        };
    }

    public Specification<Team> hasManagerLastNameContaining(String lastName) {
        return (root, query, criteriaBuilder) -> {
            if (lastName != null && !lastName.isEmpty()) {
                Join<Team, Manager> managerJoin = root.join("managers", JoinType.LEFT);
                return criteriaBuilder.like(criteriaBuilder.lower(managerJoin.get("lastName")), "%" + lastName.toLowerCase() + "%");
            }
            return criteriaBuilder.conjunction();
        };
    }
}