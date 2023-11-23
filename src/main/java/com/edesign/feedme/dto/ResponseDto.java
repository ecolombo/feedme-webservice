package com.edesign.feedme.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

	private String message;
	private Date timestamp = new Date();
	private String status;
	private Object data;
}
