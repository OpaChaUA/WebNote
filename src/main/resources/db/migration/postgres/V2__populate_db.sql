INSERT INTO users (username, password, role, enabled)
VALUES
    ('admin', '$2a$10$uzPEPPpBpCANGhowrLAacOCQMV39F.EwolkiIxTXqz4sfWhMcI0A2', 'ROLE_ADMIN', 1),
    ('testuser', '$2a$10$tNzb/bDDkaOm0ZjCXXZcCOjTG.JJm9i48JW7spETEHnqMJekYuj/y', 'ROLE_USER', 1);

INSERT INTO note (title, content, access, user_id)
VALUES
	('Title_1', 'Content_1', 'Private', 1),
	('Title_2', 'Content_2', 'Public', 1),
	('Title_3', 'Content_3', 'Private', 1),
	('Title_4', 'Content_4', 'Public', 2),
	('Title_5', 'Content_5', 'Public', 2),
	('Title_6', 'Content_6', 'Private', 2);
