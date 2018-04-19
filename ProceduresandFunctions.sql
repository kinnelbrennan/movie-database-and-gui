USE movieDB;

DROP PROCEDURE IF EXISTS Cleanse_Works_On
DELIMITER $$
CREATE PROCEDURE Cleanse_Works_On (sRole VARCHAR(255))
BEGIN
	SELECT w.Role, w.star, a.Name, f.Title
    FROM Works_on w
    JOIN Actors a
    ON a.idActors = w.idActors
    JOIN Film f
    ON f.idFilm = w.idFilm
    WHERE w.Role LIKE sRole;
END $$
DELIMITER ;