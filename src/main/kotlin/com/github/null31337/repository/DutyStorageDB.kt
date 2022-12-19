package com.github.null31337.repository

import com.github.null31337.model.Duty
import com.github.null31337.model.DutyReceive
import com.github.null31337.model.DutyStatus
import java.sql.Connection
import java.sql.Statement
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

// TODO change to liquibase migration (not enough time)
// CREATE TABLE duties (
// id SERIAL PRIMARY KEY,
// user_id BIGINT,
// name VARCHAR(255),
// description VARCHAR(255),
// deadline VARCHAR(255),
// status VARCHAR(255)
// );

class DutyStorageDB(private val connection: Connection) : DutyStorage {
  override suspend fun all(id: Long): Collection<Duty> {
    val statement = connection.prepareStatement("SELECT * FROM duties WHERE user_id = ?")
    statement.setLong(1, id)
    val resultSet = statement.executeQuery()
    val duties = mutableListOf<Duty>()
    while (resultSet.next()) {
      duties.add(Duty(
        resultSet.getLong("id"),
        resultSet.getLong("user_id"),
        resultSet.getString("name"),
        resultSet.getString("description"),
        SimpleDateFormat("dd.MM.yyyy").format(resultSet.getString("deadline").toDate()),
        DutyStatus.valueOf(resultSet.getString("status"))
      ))
    }
    return duties
  }

  override suspend fun add(userId: Long, duty: DutyReceive): Long {
    val statement = connection.prepareStatement("INSERT INTO duties (user_id, name, description, deadline, status) VALUES (?, ?, ?, ?, ?)",
      Statement.RETURN_GENERATED_KEYS
    )
    statement.setLong(1, userId)
    statement.setString(2, duty.name)
    statement.setString(3, duty.description)
    statement.setTimestamp(4, Timestamp(duty.deadline.toDate().time))
    statement.setString(5, duty.status.name)
    statement.execute()

    val resultSet = statement.generatedKeys
    return resultSet.next().let {
      resultSet.getLong(1)
    }
  }
}

private fun String.toDate(): Date {
  return SimpleDateFormat("dd.MM.yyyy").parse(this)
}

