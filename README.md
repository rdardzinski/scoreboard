# scoreboard

Assumptions:

The input string for updating score follows the format:
"{homeTeam} {homeScore} - {awayScore} {awayTeam}".
Teams and scores are separated by spaces and the dash (-).

Presenting results in the proper order to fulfill requirements requires overloading the org.sports.service.ScoreBoardService.addNewEntry method to properly manipulate the timeline when creating new entries.

# Usage example
Usage example is provided in Main class.