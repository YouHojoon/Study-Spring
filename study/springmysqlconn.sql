create table `reply`(
	rnum int primary key auto_increment,
	num int not null,
	reply text not null,
	replyer varchar(50) not null,
	relpy_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);