package com.example.data.mappers

import com.example.data.api.models.headlines.SourceDM
import com.example.domain.models.headlines.Source

fun toSource(sourceDM: SourceDM?): Source {
    return Source(sourceDM?.name, sourceDM?.id)
}

fun toSourcesList(sourcesListDM: List<SourceDM?>?): List<Source>? {
    return sourcesListDM?.map { sourceDM ->
        toSource(sourceDM)
    }
}

