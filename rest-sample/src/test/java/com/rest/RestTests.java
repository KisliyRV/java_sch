package com.rest;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {

  @Test
  public void testCreateIssue() throws IOException {
    //skipIfNotFixed(2673); // Статус - Открытый
    skipIfNotFixed(608); // Статус - Закрытый
    //skipIfNotFixed(768); // Статус - Решеный
    //skipIfNotFixed(1); // Статус - Удалить

    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    assertEquals(newIssues.size(), oldIssues.size() + 1);
    oldIssues.add(newIssue.withId(issueId).withStatus("Open"));
    assertEquals(newIssues, oldIssues);
  }

}
