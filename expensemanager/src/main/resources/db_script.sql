 insert into roles values(1,'User');
insert into roles values(2,'Admin') ;

 #user

 insert into user values(1,NOW(),'Anoop','anoop@gmail.com',1,NOW(),md5('anoop'),'anoop',2);
 insert into user values(2,NOW(),'Jovin','jovin@gmail.com',1,NOW(),md5('jovin'),'jovin',1);

# Category insert
insert into category values(1,'Food','Food');
insert into category values(2,'Electricity','Electricity');
insert into category values(3,'Internet','Internet');
insert into category values(4,'Gas','Gas');
insert into category values(5,'Water','Water');
insert into category values(6,'Newspaper','Newspaper');
insert into category values(7,'Maintanence','Maintanence');
insert into category values(8,'New Item Purchase','New Item Purchase');
insert into category values(9,'Other','Other');

