package com.insy2s.gestionbackend.filter;

import com.insy2s.gestionbackend.customenum.PresenceType;
import com.insy2s.gestionbackend.model.*;
import com.insy2s.gestionbackend.repository.SkillRepository;
import com.insy2s.gestionbackend.repository.TeamRepository;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class InternSpecifications {

    private final SkillRepository skillRepository;
    private final TeamRepository teamRepository;

    public InternSpecifications(SkillRepository skillRepository, TeamRepository teamRepository) {
        this.skillRepository = skillRepository;
        this.teamRepository = teamRepository;
    }

    public Specification<Intern> hasLastnameContaining(String lastname) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + lastname.toLowerCase() + "%");
    }

    public Specification<Intern> hasFirstnameContaining(String firstname) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + firstname.toLowerCase() + "%");
    }

    public Specification<Intern> hasPresenceType(PresenceType presenceType) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("presenceType"), presenceType);
    }

    public Specification<Intern> hasSkillId(String skillId) {
        return (root, query, criteriaBuilder) -> {
            if (skillId != null && !skillId.isEmpty()) {
                // Faire une jointure avec la table InternSkill
                Join<Intern, InternSkill> internSkillJoin = root.join("internSkills");

                // Faire une jointure avec la table Skill à partir de la table InternSkill
                Join<InternSkill, Skill> skillJoin = internSkillJoin.join("skill");

                // Retourner le predicat qui vérifie si le nom de la compétence est égal à skillName
                return criteriaBuilder.equal(skillJoin.get("skillName"), skillId);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public Specification<Intern> hasTeamId(Long teamId) {
        return (root, query, criteriaBuilder) -> {
            if (teamId != null) {
                Join<Intern, InternTeam> internTeamJoin = root.join("internTeams");

                Join<InternTeam, Team> teamJoin = internTeamJoin.join("team");

                return criteriaBuilder.equal(teamJoin.get("id"), teamId);
            }
            return criteriaBuilder.conjunction();
        };
    }
}
