package gurug.student.model;

/**
 * Created by moin on 3/10/16.
 */
/*
[
  {
    "id": 1,
    "answer": "Election Day",
    "question": "1st Tuesday after the 1st Monday in November",
    "value": 100,
    "airdate": "1985-02-08T12:00:00.000Z",
    "created_at": "2014-02-11T22:47:18.786Z",
    "updated_at": "2014-02-11T22:47:18.786Z",
    "category_id": 1,
    "game_id": null,
    "invalid_count": null,
    "category": {
      "id": 1,
      "title": "politics",
      "created_at": "2014-02-11T22:47:18.687Z",
      "updated_at": "2014-02-11T22:47:18.687Z",
      "clues_count": 30
    }
  },...
 */
public class Question {

        private String id;
        private String answer;
        private String question;
        private String value;
        private String airdate;
        private String created_at;
        private String updated_at;
        private String category_id;
        private String game_id;
        private String invalid_count;
        private String category;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAnswer() {
            return answer;
        }
        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getAirdate() {
            return airdate;
        }

        public void setAirdate(String airdate) {
            this.airdate = airdate;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String zipFileUrl) {
            this.updated_at = updated_at;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getGame_id() {
            return game_id;
        }

        public void setGame_id(String game_id) {
            this.game_id = game_id;
        }

        public String getInvalid_count() {
            return invalid_count;
        }

        public void setInvalid_count(String invalid_count) {
            this.invalid_count = invalid_count;
        }

        public String getCategory() {
        return category;
    }

        public void setCategory(String category) {
        this.category = category;
    }
    }


