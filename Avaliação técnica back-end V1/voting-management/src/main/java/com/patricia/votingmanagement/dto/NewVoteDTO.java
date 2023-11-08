package com.patricia.votingmanagement.dto;

import com.patricia.votingmanagement.enums.VoteValueEnum;

import jakarta.validation.constraints.NotNull;

public record NewVoteDTO(
		@NotNull Long associateId,
		@NotNull Long sessionId,
		@NotNull VoteValueEnum voteValueEnum
		) {
			
}
