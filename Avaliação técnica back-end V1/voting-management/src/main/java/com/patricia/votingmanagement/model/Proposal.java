package com.patricia.votingmanagement.model;

import org.hibernate.validator.constraints.Length;

import com.patricia.votingmanagement.enums.ProposalStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "proposal")
public class Proposal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "prp_id")
	private Long id;
	
	@Column(name = "prp_description")
	@NotNull
	@Length(min = 10, max = 300)
	private String description;
	
	@Column(name = "prp_status")
	@NotNull
	private ProposalStatusEnum status;
	
	public Proposal() {}
	
	public Proposal(String description) {
		super();
		this.description = description;
		this.status = ProposalStatusEnum.PROPOSAL_CREATED;
	}

	public Long getId() {
		return id;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProposalStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ProposalStatusEnum status) {
		this.status = status;
	}
	

}
