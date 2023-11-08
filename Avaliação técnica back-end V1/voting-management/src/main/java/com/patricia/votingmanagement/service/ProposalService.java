package com.patricia.votingmanagement.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patricia.votingmanagement.dto.NewProposalDTO;
import com.patricia.votingmanagement.dto.ProposalDTO;
import com.patricia.votingmanagement.dto.mapper.ProposalMapper;
import com.patricia.votingmanagement.enums.ProposalStatusEnum;
import com.patricia.votingmanagement.exception.NotFoundException;
import com.patricia.votingmanagement.model.Proposal;
import com.patricia.votingmanagement.repository.ProposalRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProposalService {

	@Autowired	
	private ProposalRepository proposalRepository;
	
	@Autowired
	private ProposalMapper proposalMapper;
	
	public ProposalDTO newProposal(NewProposalDTO newProposalDTO) {
		Proposal proposalEntity = new Proposal(newProposalDTO.description());
		return proposalMapper.toDTO(proposalRepository.save(proposalEntity));
	}
	
	public List<ProposalDTO> findAllProposals() {
		return proposalRepository.findAll()
		.stream()
		.map(proposalMapper::toDTO)
		.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
	}
	
	public void validateProposalExists(Long id) {
		if(proposalRepository.findById(id).isEmpty()) { 
			throw new NotFoundException(id, "Proposal");
		}
	}
	
	public Proposal getProposalById(Long id) {
		validateProposalExists(id);
		return proposalRepository.findById(id).get();
	}
	
	@Transactional
	public void updateProposalResults(Long proposalId, Long yesVotes, Long noVotes) {
		Proposal proposal = proposalRepository.findById(proposalId).orElseThrow();
		if (yesVotes.compareTo(noVotes) == 0) {
			proposal.setStatus(ProposalStatusEnum.TIE);
		} else if (yesVotes.compareTo(noVotes) > 0) {
			proposal.setStatus(ProposalStatusEnum.PROPOSAL_ACCEPTED);
		}
		else {
			proposal.setStatus(ProposalStatusEnum.PROPOSAL_REJECTED);
		} 
		proposalRepository.saveAndFlush(proposal);
	}
}
