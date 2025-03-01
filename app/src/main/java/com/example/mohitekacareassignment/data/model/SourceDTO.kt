package com.example.mohitekacareassignment.data.model

import com.example.mohitekacareassignment.domain.model.Source

data class SourceDTO(
    val id: String?,
    val name: String?
)

fun SourceDTO.toDomainSource() = Source(
    id = this.id ?: "",
    name = this.name ?: ""
)