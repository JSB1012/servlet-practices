-- EmaillistDao

desc emaillist;

-- findAll
SELECT 
    no, first_name, last_name, email
FROM
    emaillist
ORDER BY no DESC;

-- insert
insert into emaillist values(null, '둘', '리', 'dooly@gmail.com');	