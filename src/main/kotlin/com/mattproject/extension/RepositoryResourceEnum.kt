package com.mattproject.extension

enum class RepositoryResourceEnum {
    REPOSITORY,
    METHOD,
    FIELD,
    DISTINCT,
    DISTINCTFIELD,
    WHERE,
    OFFSET,
    LIMIT,
    ORDER;
    companion object {
        fun required(): Array<RepositoryResourceEnum> {
            return arrayOf(REPOSITORY, METHOD)
        }

        fun queryFields() :Array<RepositoryResourceEnum> {
            return arrayOf(FIELD, DISTINCT, DISTINCTFIELD, WHERE, OFFSET, LIMIT, ORDER)
        }
    }
}