/*
SQLyog Trial v13.1.5  (64 bit)
MySQL - 8.0.17 : Database - course
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`course` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `course`;

/*Table structure for table `table_admin` */

DROP TABLE IF EXISTS `table_admin`;

CREATE TABLE `table_admin` (
  `Id` int(255) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `Rname` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `Age` int(255) DEFAULT NULL,
  `Birthday` datetime DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Tel` varchar(255) DEFAULT NULL,
  `PersonID` varchar(255) NOT NULL,
  `Picture` varchar(255) DEFAULT NULL,
  `State` int(11) DEFAULT '1',
  `CreateTime` datetime DEFAULT NULL,
  `UpdateTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `Role` int(255) DEFAULT NULL,
  PRIMARY KEY (`Id`,`PersonID`),
  KEY `Role` (`Role`),
  CONSTRAINT `table_admin_ibfk_1` FOREIGN KEY (`Role`) REFERENCES `table_role` (`role_Id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `table_admin` */

insert  into `table_admin`(`Id`,`Name`,`Rname`,`Password`,`Age`,`Birthday`,`Email`,`Tel`,`PersonID`,`Picture`,`State`,`CreateTime`,`UpdateTime`,`Role`) values 
(1,'root','张三','123123',22,'2020-05-13 09:49:56','110120130@qq.com','110120130140','41223xxxxx5555555','xxx/xxp.jpg',1,'2020-05-13 09:50:24','2020-05-13 09:50:31',1);

/*Table structure for table `table_ass_major` */

DROP TABLE IF EXISTS `table_ass_major`;

CREATE TABLE `table_ass_major` (
  `table_major_graduation_requirements_tmgr_id` int(255) NOT NULL,
  `table_association_graduation_requirements_tagr_id` int(255) NOT NULL,
  PRIMARY KEY (`table_major_graduation_requirements_tmgr_id`,`table_association_graduation_requirements_tagr_id`),
  KEY `table_ass_major_table_association_graduation_requirements_fk` (`table_association_graduation_requirements_tagr_id`),
  CONSTRAINT `table_ass_major_table_association_graduation_requirements_fk` FOREIGN KEY (`table_association_graduation_requirements_tagr_id`) REFERENCES `table_association_graduation_requirements` (`tagr_id`),
  CONSTRAINT `table_ass_major_table_major_graduation_requirements_fk` FOREIGN KEY (`table_major_graduation_requirements_tmgr_id`) REFERENCES `table_major_graduation_requirements` (`tmgr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `table_ass_major` */

insert  into `table_ass_major`(`table_major_graduation_requirements_tmgr_id`,`table_association_graduation_requirements_tagr_id`) values 
(1,1),
(10,1),
(1,2),
(2,2),
(1,3),
(2,3),
(3,3),
(3,4),
(4,4),
(4,5),
(5,5),
(5,6),
(6,6),
(6,7),
(7,7),
(7,8),
(8,8),
(8,9),
(9,9),
(9,10),
(10,11),
(10,12);

/*Table structure for table `table_association_graduation_requirements` */

DROP TABLE IF EXISTS `table_association_graduation_requirements`;

CREATE TABLE `table_association_graduation_requirements` (
  `tagr_id` int(255) NOT NULL AUTO_INCREMENT,
  `tagr_title` varchar(255) DEFAULT NULL,
  `tagr_describe` text,
  PRIMARY KEY (`tagr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `table_association_graduation_requirements` */

insert  into `table_association_graduation_requirements`(`tagr_id`,`tagr_title`,`tagr_describe`) values 
(1,'工程知识','能够将数学、自然科学、工程基础和专业知识用于解决复杂工程问题。'),
(2,'问题分析','能够应用数学、自然科学和工程科学的基本原理，识别、表达、并通过文献研究分析复杂工程问题，以获得有效结论。'),
(3,'设计/开发解决方案','能够设计针对复杂工程问题的解决方案，设计满足特定需求的系统、单元（部件）或工艺流程，并能够在设计环节中体现创新意识，考虑社会、健康、安全、法律、文化以及环境等因素。'),
(4,'研究','能够基于科学原理并采用科学方法对复杂工程问题进行研究，包括设计实验、分析与解释数据、并通过信息综合得到合理有效的结论。'),
(5,'使用现代工具','能够针对复杂工程问题，开发、选择与使用恰当的技术、资源、现代工程工具和信息技术工具，包括对复杂工程问题的预测与模拟，并能够理解其局限性。'),
(6,'工程与社会',' 能够基于工程相关背景知识进行合理分析，评价专业工程实践和复杂工程问题解决方案对社会、健康、安全、法律以及文化的影响，并理解应承担的责任。'),
(7,'环境与可持续发展','能够理解和评价针对复杂工程问题的专业工程实践对环境、社会可持续发展的影响。'),
(8,'职业规划','具有人文社会科学素养、社会责任感，能够在工程实践中理解并遵守工程职业道德和规范，履行责任。'),
(9,'个人和团队','能够在多学科背景下的团队中承担个体、团队成员以及负责人的角色。'),
(10,'沟通','能够就复杂工程问题与业界同行及社会公众进行有效沟通和交流，包括撰写报告和设计文稿、陈述发言、清晰表达或回应指令。并具备一定的国际视野，能够在跨文化背景下进行沟通和交流。'),
(11,'项目管理','理解并掌握工程管理原理与经济决策方法，并能在多学科环境中应用。\r\n'),
(12,'终身学习','具有自主学习和终身学习的意识，有不断学习和适应发展的能力。');

/*Table structure for table `table_course` */

DROP TABLE IF EXISTS `table_course`;

CREATE TABLE `table_course` (
  `tc_id` int(255) NOT NULL AUTO_INCREMENT,
  `tc_name` varchar(255) DEFAULT NULL,
  `tc_teacher` int(255) DEFAULT NULL,
  `tc_showid` varchar(255) DEFAULT NULL,
  `tc_score` double(5,2) DEFAULT NULL,
  `tc_describe` text,
  `tc_usual_performance` int(255) DEFAULT '0',
  `tc_experimental_results` int(255) DEFAULT '0',
  `tc_interim_results` int(255) DEFAULT '0',
  `tc_final_exam` int(255) DEFAULT '0',
  `tc_report_results` int(255) DEFAULT '0',
  `tc_training_results` int(255) DEFAULT '0',
  `tc_extracurricular_performance` int(255) DEFAULT '0',
  PRIMARY KEY (`tc_id`),
  KEY `tc_teacher` (`tc_teacher`),
  CONSTRAINT `table_course_ibfk_1` FOREIGN KEY (`tc_teacher`) REFERENCES `table_user` (`u_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `table_course` */

insert  into `table_course`(`tc_id`,`tc_name`,`tc_teacher`,`tc_showid`,`tc_score`,`tc_describe`,`tc_usual_performance`,`tc_experimental_results`,`tc_interim_results`,`tc_final_exam`,`tc_report_results`,`tc_training_results`,`tc_extracurricular_performance`) values 
(1,'C++课程设计',3,'06031601',2.00,'C++语言是当今最实用的计算机高级编程语言之一，也是通用程序设计的重要基础。本课程是《程序设计基础(I)》课程的延续，在前期课程基础上，进一步学习掌握C++面向对象程序设计的基本知识。重点是通过上机实践，熟练掌握C++语言要素，培养分析问题解决问题的能力，增强实践能力。本课程安排在大二小学期，作为该学期主要课程，保障学生在4周时间内集中学习并完成课程设计。\r\n\r\n教学内容：【注，延续程序设计基础I。若前期学过Java程序设计，可适当裁剪以下内容】\r\n\r\n第9章 类和对象\r\n\r\n第10章 构造函数与析构函数\r\n\r\n第11章 类的继承\r\n\r\n第12章 运算符重载\r\n\r\n第13章 模板\r\n\r\n第14章 输入输出流\r\n\r\n第15章 异常\r\n\r\n第16章 ANSI C++标准语法补充\r\n\r\n实践内容：\r\n\r\n1、自由选题、确定设计目标；\r\n\r\n2、分析问题、确定解决方案；\r\n\r\n3、编程实现、测试验证；\r\n\r\n4、演示说明、答辩总结。',0,0,0,100,0,0,0),
(3,'distributed systems',22,'06020502',2.00,'《分布式系统》是为“软件工程”及“计算机科学与技术”专业的学生开设的一门专业选修课。随着多机文件共享到广义的资源贡献，从单一的计算模型到多层次的计算模型，从封闭的局部网络到开放的全球网络。分布式系统已演化成近代计算机系统的基本组织结构，支持非常广泛的工业、商业应用。本课程的设置能够启发学生归纳和总结在分布式系统的分析、设计和实现中遇到的常见问题、通过介绍经典的算法和思想帮助学生理解分布式系统的工作原理，为学生今后从事通信、并发、网络等研发工作打下良好的基础。',0,0,0,100,0,0,0),
(4,'J2EE实用基础',23,'06027801',2.00,'J2EE（Java 2 Platform Enterprise Edition，Java平台企业级版本）是一个基于Java语言的主流的企业级应用程序开发平台，作为新型软件方法研究的最新领域，受到教育学者和业界的普遍重视。\r\n\r\n本课程主要讲述J2EE的基础知识和初步开发技术，指导学生掌握开发J2EE应用的技术和J2EE平台的构建及管理。课程内容包括：第一章J2EE概述、第二章Servlet技术、第三章JSP技术和第四章JDBC技术。\r\n\r\n修学本课程的学生应该具备Java程序设计、数据库技术、HTML和Http网络协议相关的知识。',0,0,0,100,0,0,0),
(5,'JAVA课程设计',21,'06030201',2.00,'Java程序设计是计算机学科及电子信息学科各专业本科的一门专业基础课程，作为程序设计课程和面向对象方法的训练课程。通过全面、系统地介绍Java语言的基本知识、运行机制、多种编程方法和技术，使学生理解和掌握面向对象的程序设计方法，理解和掌握网络程序的特点和设计方法，建立起牢固扎实的理论基础，具备综合应用程序的设计能力。\r\n\r\n课程设计是巩固所学理论知识、提高程序设计能力的重要实践环节。通过课程设计的训练，使学生能够综合应用Java的基础知识和基本方法，体会软件设计的全过程，编写出高效率的应用程序，培养学生的数据处理能力，提高软件设计能力，从而为今后进行系统软件和应用软件的开发研究打下坚实的基础，并倡导刻苦钻研的精神和严谨的治学作风。',0,0,0,100,0,0,0),
(6,'TCP/IP原理与技术',22,'06023502',2.00,'学科背景：TCP/IP协议是目前网络中普遍采用的通信协议，TCP/IP技术是Internet的核心技术，也是计算机科学与技术、网络工程、软件工程、通信工程以及电大类专业学生必须掌握的基本知识。TCP/IP技术体现了很多很好的设计思想和理念，因此，对于培养学生的思维方式和设计能力有很大的作用。本课程的目的是使学生通过本课程的学习掌握TCP/IP协议的基本原理和技术，为学生进一步学习互连网的相关知识及将来从事互连网方面的工作打下一个良好的基础。\r\n\r\n教学内容：本课程以介绍TCP/IP的原理和方法为主，自底向上地讲解TCP/IP协议的Internet层、传输层和应用层的原理和技术。本课程的教学内容主要包括：底层网络技术与互联网体系结构、IP地址与子网划分、地址解析、IP协议、差错与控制报文协议、IP路由、传输层协议、域名系统、引导协议与动态主机配置协议、IP组播、文件传输协议、邮件传输协议、远程登录协议、超文本传输协议、简单网络管理协议、移动IP、新一代因特网协议。\r\n\r\n预备知识：学习本课程所需的预备知识为计算机导论和计算机网络的基本知识。\r\n\r\n 教学方式：本课程以理论课授课为主。考试方式为闭卷考试，平时成绩作为参考。',0,0,0,100,0,0,0),
(7,'web技术与应用',23,'06029903',2.00,'作为现代互联网服务的主要形式和主流技术，Web开发技术在信息网络化、数字化过程中起着举足轻重的作用。本课程讲授Web开发过程中所涉及到HTML、CSS、JavaScript、Web Service、Web Browser等众多技术内容和主流实现手段，通过课堂讲解、上机实验、课后分析等教学环节和手段，培养学生对Web技术的理解应用能力和Web服务的规划管理水平。本课程内容不仅仅局限于网页设计、网站建设等传统Web教学内容，在内容选择上贴近互联网技术发展前沿和工业界主流应用现状，对jQuery、DOM、PHP、Ajax等具有现实意义的主流技术进行详细介绍，努力培养学生的Web技术高级开发和应用能力，充分挖掘学生的学习兴趣和未来应用潜力。\r\n\r\n 选修本课程的学生需要先修计算机网络课程，同时具备一定的面向对象高级语言编程经验。',0,0,0,100,0,0,0),
(8,'WEB服务技术与应用开发',21,'06029905',2.00,'\r\n《WEB服务技术与应用开发》是计算机科学与技术专业的一门专业必修课。本课程以理论和实际应用相结合，使学生通过本课程的学习，熟悉Web技术的各种基本概念、体系结构和主要组成，并达到一定的应用水平。掌握一种主流的Web集成技术，熟悉其配置和应用环境，能与传统程序设计和数据库开发技术相结合，并能根据实际问题，能独立设计、开发、调试完成一个Web应用。\r\n\r\n\r\n\r\n修学本课程的学生应该具备JAVA语言程序设计、网络基础、网页设计、数据库等学科的基础。\r\n\r\n\r\n\r\n\r\n\r\n',0,0,0,100,0,0,0),
(9,'中国近现代史纲要',22,'EEAP017',2.00,'1',0,0,0,100,0,0,0),
(10,'人工智能与机器人',23,'06023001',2.00,'人工智能与机器人是智能科学与计算机科学的核心课程。教学内容包括人工智能和智能系统的概况，介绍人工智能的主要研究内容及相应的算法，列举出人工智能的应用领域，主要包括在智能机器人方面的应用。研究传统人工智能的知识表示方法和搜索推理技术，包括状态空间法、问题归约法谓词逻辑法、语义网络法、盲目搜索、启发式搜索、规则演绎算法和产生式系统等；讨论高级知识推理，涉及非单调推理、时序推理、和各种不确定推理方法；探讨人工智能的新研究领域，初步阐述计算智能的基本知识，包含神经计算、模糊计算、进化计算和人工生命诸内容；比较详细地讨论了人工智能的主要应用，包括专家系统、机器学习、自动规划、Agent、自然语言理解、机器视觉和智能控制等；简要介绍智能移动机器人的发展现状及历史；详细介绍机器人的运动学，动力学等，介绍机器人使用的相关传感器，研究人工智能的相关方法在移动自主机器人领域的使用，包括图像处理、计算机视觉等，并结合相关方法详细讨论机器人的定位、规划和导航问题。先修课程为数据结构、算法设计、离散数学、程序设计等。考核方式为：期末闭卷考试70% + 平时作业30%。',0,0,0,100,0,0,0),
(11,'人机交互',21,'06035301',2.00,'人机交互是一门跨学科研究，它的研究内容很广，包括心理学领域的认知科学，心理学；软件工程领域的系统构架技术；信息处理领域的语音处理技术和图像处理技术；人工智能领域的智能控制技术等。在美国21世纪信息技术计划中，人机交互被列为四大基础研究内容之一。因此，智能科学与技术专业的学生选修这门课程十分必要。通过教学，使学生能够了解人机交互技术的发展方向，拓宽知识面，具备国际视野，并通过工程实例培养学生的创新性和动手能力。',0,0,0,100,0,0,0),
(12,'人机交互技术',22,'06029202',3.00,'人机交互技术已成为21世纪信息领域亟待解决的重大课题之一。在美国21世纪信息技术计划中，软件、人机交互、网络、高性能计算被列为四大基础研究内容。所以计算机类专业的学生要跟上时代的需要，选修这门课程是十分必要的。人机交互技术的目的是充分利用现代计算机的软硬件设计，使计算机设备更易于人的使用，更具人性化。使学生能够了解人机交互技术的发展方向，拓宽知识面，具备国际视野，了解前沿科技，并通过工程实例培养学生的创新性和动手能力，为人机物融合处理方向的研究与开发打下基础。',0,0,0,100,0,0,0),
(13,'体育（Ⅰ）',23,'EEAP012',2.00,' ',0,0,0,100,0,0,0),
(14,'体育（Ⅱ）',21,'EEAP013',2.00,' ',0,0,0,100,0,0,0),
(26,'test2',23,'EEAP014',2.00,NULL,0,0,0,100,0,0,0);

/*Table structure for table `table_eq_kp` */

DROP TABLE IF EXISTS `table_eq_kp`;

CREATE TABLE `table_eq_kp` (
  `teqkp_id` int(255) NOT NULL AUTO_INCREMENT,
  `teqkp_eq_id` int(255) DEFAULT NULL COMMENT '试题id',
  `teqkp_kp_id` int(255) DEFAULT NULL COMMENT '知识点id',
  PRIMARY KEY (`teqkp_id`),
  KEY `teqkp_eq_id` (`teqkp_eq_id`),
  KEY `teqkp_kp_id` (`teqkp_kp_id`),
  CONSTRAINT `table_eq_kp_ibfk_1` FOREIGN KEY (`teqkp_eq_id`) REFERENCES `table_examination_questions` (`teq_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `table_eq_kp_ibfk_2` FOREIGN KEY (`teqkp_kp_id`) REFERENCES `table_knowledge_points` (`tkp_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `table_eq_kp` */

insert  into `table_eq_kp`(`teqkp_id`,`teqkp_eq_id`,`teqkp_kp_id`) values 
(1,1,1),
(2,1,2),
(3,1,3),
(4,1,4),
(5,1,5),
(6,1,6),
(9,12,2),
(10,12,42);

/*Table structure for table `table_examination_questions` */

DROP TABLE IF EXISTS `table_examination_questions`;

CREATE TABLE `table_examination_questions` (
  `teq_id` int(255) NOT NULL AUTO_INCREMENT,
  `teq_name` varchar(255) DEFAULT NULL,
  `teq_score` int(255) DEFAULT NULL,
  `teq_describe` varchar(255) DEFAULT NULL,
  `teq_test_id` int(255) DEFAULT NULL COMMENT '考试的id',
  PRIMARY KEY (`teq_id`),
  KEY `teq_test_id` (`teq_test_id`),
  CONSTRAINT `table_examination_questions_ibfk_1` FOREIGN KEY (`teq_test_id`) REFERENCES `table_test` (`tt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `table_examination_questions` */

insert  into `table_examination_questions`(`teq_id`,`teq_name`,`teq_score`,`teq_describe`,`teq_test_id`) values 
(1,'一、\r\n选择题',10,'无',1),
(2,'二、填空题',15,'无',1),
(3,'三、简答题',25,'无',1),
(4,'四、计算题',35,'无',1),
(5,'五、课外题',15,'无',1),
(12,'补充',50,'无',15);

/*Table structure for table `table_funs` */

DROP TABLE IF EXISTS `table_funs`;

CREATE TABLE `table_funs` (
  `fun_Id` int(255) NOT NULL AUTO_INCREMENT,
  `fun_Name` varchar(255) DEFAULT NULL,
  `fun_Url` varchar(255) DEFAULT NULL,
  `fun_Target` varchar(255) DEFAULT NULL,
  `fun_Pid` int(255) DEFAULT NULL,
  PRIMARY KEY (`fun_Id`),
  KEY `fun_Pid` (`fun_Pid`),
  CONSTRAINT `table_funs_ibfk_1` FOREIGN KEY (`fun_Pid`) REFERENCES `table_funs` (`fun_Id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `table_funs` */

insert  into `table_funs`(`fun_Id`,`fun_Name`,`fun_Url`,`fun_Target`,`fun_Pid`) values 
(4,'用户管理',NULL,NULL,NULL),
(5,'查询用户',NULL,NULL,4),
(6,'修改用户权限',NULL,NULL,4),
(7,'试卷管理',NULL,NULL,NULL),
(8,'查看试卷',NULL,NULL,7),
(9,'编辑试卷',NULL,NULL,7),
(10,'添加试卷',NULL,NULL,7),
(11,'删除试卷',NULL,NULL,7),
(12,'成绩管理',NULL,NULL,NULL),
(13,'查看成绩',NULL,NULL,12),
(14,'添加成绩表',NULL,NULL,12),
(15,'修改成绩权重',NULL,NULL,12),
(16,'学生成绩管理',NULL,NULL,NULL),
(17,'查询成绩',NULL,NULL,16);

/*Table structure for table `table_index_points` */

DROP TABLE IF EXISTS `table_index_points`;

CREATE TABLE `table_index_points` (
  `tip_id` int(255) NOT NULL AUTO_INCREMENT,
  `table_course_tc_id` int(255) NOT NULL,
  `table_major_graduation_requirements_tmgr_id` int(255) NOT NULL,
  `tip_describe` text,
  `tip_weight_level` varchar(2) DEFAULT NULL,
  `tip_weight` int(255) DEFAULT NULL,
  `tip_part` int(255) DEFAULT NULL,
  PRIMARY KEY (`tip_id`),
  KEY `table_index_points_table_course_fk` (`table_course_tc_id`),
  KEY `table_index_points_table_major_graduation_requirements_fk` (`table_major_graduation_requirements_tmgr_id`),
  CONSTRAINT `table_index_points_table_course_fk` FOREIGN KEY (`table_course_tc_id`) REFERENCES `table_course` (`tc_id`),
  CONSTRAINT `table_index_points_table_major_graduation_requirements_fk` FOREIGN KEY (`table_major_graduation_requirements_tmgr_id`) REFERENCES `table_major_graduation_requirements` (`tmgr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `table_index_points` */

insert  into `table_index_points`(`tip_id`,`table_course_tc_id`,`table_major_graduation_requirements_tmgr_id`,`tip_describe`,`tip_weight_level`,`tip_weight`,`tip_part`) values 
(1,1,1,'掌握数学、自然科学知识，具有逻辑思维和逻辑推理能力','H',10,1),
(2,3,1,NULL,'H',10,1),
(3,4,1,NULL,'H',10,1),
(4,5,1,NULL,'H',10,1),
(5,6,1,NULL,'H',10,1),
(6,7,1,NULL,'H',10,1),
(7,8,1,NULL,'H',10,1),
(8,9,1,NULL,'H',10,1),
(9,10,1,NULL,'H',10,1),
(10,1,1,'具备计算机类专业的工程基础知识和解决复杂工程问题的能力，能够运用所学知识进行复杂工程问题求解','H',10,2),
(11,3,1,NULL,'H',10,2),
(12,4,1,NULL,'H',10,2),
(13,5,1,NULL,'H',10,2),
(14,6,1,NULL,'H',10,2),
(15,7,1,NULL,'H',10,2),
(16,8,1,NULL,'H',10,2),
(17,9,1,NULL,'H',10,2),
(18,10,1,NULL,'H',10,2),
(19,1,1,'了解国防及智能科学领域的相关知识，了解计算机专业知识、方法和技术在该领域的应用背景、发展现状和趋势，能够判别计算机系统的复杂性','H',10,3),
(20,3,1,NULL,'H',10,3),
(21,4,1,NULL,'H',10,3),
(22,5,1,NULL,'H',10,3),
(23,6,1,NULL,'H',10,3),
(24,7,1,NULL,'H',10,3),
(25,8,1,NULL,'H',10,3),
(26,9,1,NULL,'H',10,3),
(27,10,1,NULL,'H',10,3);

/*Table structure for table `table_knowledge_points` */

DROP TABLE IF EXISTS `table_knowledge_points`;

CREATE TABLE `table_knowledge_points` (
  `tkp_id` int(255) NOT NULL AUTO_INCREMENT,
  `tkp_title` varchar(255) DEFAULT NULL,
  `tkp_describe` text,
  `table_course_tc_id` int(255) NOT NULL,
  `tkp_part` int(10) DEFAULT NULL,
  PRIMARY KEY (`tkp_id`),
  KEY `table_knowledge_points_table_course_fk` (`table_course_tc_id`),
  CONSTRAINT `table_knowledge_points_table_course_fk` FOREIGN KEY (`table_course_tc_id`) REFERENCES `table_course` (`tc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `table_knowledge_points` */

insert  into `table_knowledge_points`(`tkp_id`,`tkp_title`,`tkp_describe`,`table_course_tc_id`,`tkp_part`) values 
(1,'类和对象','9.1 类\r\n9.2 对象\r\n9.3 this指针',1,1),
(2,'构造函数和析构函数','10.1 构造函数\r\n10.2 析构函数\r\n10.3 拷贝构造函数和单参构造函数\r\n10.4 复合对象和成员对象\r\n10.5 对象数组\r\n10.6 静态成员\r\n10.7 const和volatile修饰符\r\n10.8 指针成员\r\n10.9 引用成员\r\n10.10 类成员的指针',1,2),
(3,'类的继承','11.1 继承和派生\r\n11.2 派生类的构造和析构\r\n11.3 二义性问题\r\n11.4 虚基类\r\n11.5 子类型关系\r\n11.6 虚函数\r\n11.7 例子：对象的链表\r\n11.8 继承性设计要点',1,3),
(4,'运算符重载','12.1 一般运算符重载\r\n12.2 用友元函数实现运算符重载\r\n12.3 特殊运算符的重载',1,4),
(5,'模版','13.1 模版的概念\r\n13.2 函数模版\r\n13.3 类模版\r\n13.4 STL简介',1,5),
(6,'输入输出流','14.1 概述\r\n14.2 基本流类\r\n14.3 标准输入输出\r\n14.4 文件流\r\n14.5 例子：学生分数管理',1,6),
(11,'Introduction','分布式系统的定义和主要特点',3,1),
(12,'Architectures','三种主要架构',3,2),
(13,'Code Migration','代码迁移的目的和方法',3,3),
(14,'Virtualization','虚拟化的定义和主流技术',3,4),
(15,'Communication','RPC流程',3,5),
(16,'Naming','命名的必要性、基于Home的方法、DNS',3,6),
(17,'Synchronization','三种时钟的定义和使用场景、互斥锁和选举算法',3,7),
(18,'Consistency & Replication','几种一致性模型',3,8),
(19,'Fault Tolerance','几种典型错误和恢复方法',3,9),
(20,'一致hash算法','一致hash算法的内容和使用方法',3,10),
(21,'test','xxxxxx',4,1),
(22,'J2EE简介','J2EE一般介绍',4,1),
(23,'分布式多层应用程序','了解分布式多层应用程序',4,1),
(24,'J2EE容器','了解容器的概念与原理',4,1),
(25,'servlet接口','掌握servlet接口及其开发应用',4,2),
(26,'servlet环境','掌握配置servlet环境方法',4,2),
(27,'会话','掌握会话的跟踪与管理',4,2),
(28,'过滤器','掌握过滤器的原理与应用',4,2),
(29,'jsp页面','掌握JSP页面的构成元素',4,3),
(30,'JSP的生命周期','掌握Jsp的转换与事件监听',4,3),
(31,'JSP的内置对象','掌握JSP的内置(隐式)对象',4,3),
(32,'JavaBean','掌握JavaBean开发要求与条件',4,3),
(33,'JDBC驱动程序','了解JDBC驱动程序',4,4),
(34,'数据库连接','掌握Connections；Statements；Result Sets的应用与开发',4,4),
(35,'元数据','掌握数据库元数据的获取与应用',4,4),
(36,'集成开发环境的应用与图形用户界面','讲授设计要求，开发工具介绍\r\n图形用户界面、软件开发过程介绍Junit应用JDBC应用',5,1),
(37,'选题与需求分析','根据选题设计软件体系结构，确定系统功能',5,2),
(38,'开发过程的实施','上机、编码、调试、答疑指导',5,3),
(39,'汇报演示,项目验收','汇报演示,项目验收',5,4),
(42,'test',NULL,26,1);

/*Table structure for table `table_kp_index_points` */

DROP TABLE IF EXISTS `table_kp_index_points`;

CREATE TABLE `table_kp_index_points` (
  `tip_kp_id` int(255) NOT NULL AUTO_INCREMENT COMMENT '指标id',
  `table_knowledge_points_tkp_id` int(255) NOT NULL,
  `table_major_g` int(255) DEFAULT NULL COMMENT '毕业要求',
  `table_part` int(255) DEFAULT NULL COMMENT '被勾选的指标点',
  PRIMARY KEY (`tip_kp_id`),
  KEY `table_major_g` (`table_major_g`),
  KEY `table_knowledge_points_tkp_id` (`table_knowledge_points_tkp_id`),
  CONSTRAINT `table_kp_index_points_ibfk_1` FOREIGN KEY (`table_major_g`) REFERENCES `table_major_graduation_requirements` (`tmgr_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `table_kp_index_points_ibfk_2` FOREIGN KEY (`table_knowledge_points_tkp_id`) REFERENCES `table_knowledge_points` (`tkp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `table_kp_index_points` */

insert  into `table_kp_index_points`(`tip_kp_id`,`table_knowledge_points_tkp_id`,`table_major_g`,`table_part`) values 
(1,1,1,1),
(2,1,1,2),
(3,1,1,3),
(4,1,2,1),
(5,1,2,2),
(6,1,2,3),
(10,42,2,1);

/*Table structure for table `table_major_graduation_requirements` */

DROP TABLE IF EXISTS `table_major_graduation_requirements`;

CREATE TABLE `table_major_graduation_requirements` (
  `tmgr_id` int(255) NOT NULL AUTO_INCREMENT,
  `tmgr_tile` varchar(255) DEFAULT NULL,
  `tmgr_describe` text,
  PRIMARY KEY (`tmgr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `table_major_graduation_requirements` */

insert  into `table_major_graduation_requirements`(`tmgr_id`,`tmgr_tile`,`tmgr_describe`) values 
(1,'毕业要求1','能够将数学、自然科学、工程基础和计算机专业知识用于解决复杂工程问题。'),
(2,'毕业要求2','能够应用数学、自然科学和工程科学的基本原理，识别、表达、并通过文献研究分析复杂工程问题，以获得有效结论。'),
(3,'毕业要求3','能够设计针对复杂工程问题的解决方案，设计满足特定需求的系统、模块（组件）或算法流程，并能够在设计环节中体现创新意识，考虑社会、健康、安全、法律、文化以及环境等因素，运用所学理论和技术手段进行计算机系统和应用软件的分析、设计和开发。'),
(4,'毕业要求4','能够基于科学原理并采用科学方法对复杂工程问题进行研究，包括设计计算机软硬件实验、分析与解释数据、并通过信息综合得到合理有效的结论。'),
(5,'毕业要求5','能够针对复杂工程问题，开发、选择与使用恰当的技术、资源、现代工程工具和信息技术工具，包括对复杂工程问题的预测与模拟，并能够理解其局限性。'),
(6,'毕业要求6','能够基于工程相关背景知识进行合理分析，评价计算机类专业工程实践和复杂工程问题解决方案对社会、健康、安全、法律以及文化的影响，并理解应承担的责任。'),
(7,'毕业要求7','能够理解和评价针对复杂工程问题的计算机工程实践对环境、社会可持续发展的影响。'),
(8,'毕业要求8','具有人文社会科学素养、社会责任感，能够在工程实践中理解并遵守工程职业道德和规范，履行责任。'),
(9,'毕业要求9','能够在多学科背景下的团队中承担个体、团队成员以及负责人的角色。'),
(10,'毕业要求10','能够就复杂工程问题与业界同行及社会公众进行有效沟通和交流，包括撰写报告和设计文稿、陈述发言、清晰表达或回应指令。并具备一定的国际视野，能够在跨文化背景下进行沟通和交流。'),
(11,'毕业要求11','理解并掌握工程管理原理与经济决策方法，并能在多学科环境中应用。'),
(12,'毕业要求12','具有自主学习和终身学习的意识，有不断学习和适应发展的能力。');

/*Table structure for table `table_permissions` */

DROP TABLE IF EXISTS `table_permissions`;

CREATE TABLE `table_permissions` (
  `role_id` int(255) DEFAULT NULL,
  `funs_id` int(255) DEFAULT NULL,
  KEY `role_id` (`role_id`),
  KEY `funs_id` (`funs_id`),
  CONSTRAINT `table_permissions_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `table_role` (`role_Id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `table_permissions_ibfk_2` FOREIGN KEY (`funs_id`) REFERENCES `table_funs` (`fun_Id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `table_permissions` */

insert  into `table_permissions`(`role_id`,`funs_id`) values 
(1,4),
(1,7),
(1,12),
(2,4),
(2,7),
(2,12),
(3,16);

/*Table structure for table `table_role` */

DROP TABLE IF EXISTS `table_role`;

CREATE TABLE `table_role` (
  `role_Id` int(255) NOT NULL AUTO_INCREMENT,
  `role_Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `table_role` */

insert  into `table_role`(`role_Id`,`role_Name`) values 
(1,'管理员'),
(2,'老师'),
(3,'学生');

/*Table structure for table `table_stu_test` */

DROP TABLE IF EXISTS `table_stu_test`;

CREATE TABLE `table_stu_test` (
  `tst_id` int(255) NOT NULL AUTO_INCREMENT,
  `tst_test_name` varchar(255) DEFAULT NULL,
  `tst_term` varchar(255) DEFAULT NULL,
  `tst_test_type` varchar(255) DEFAULT NULL,
  `tst_test_second_test` varchar(5) DEFAULT NULL,
  `tst_teacher` int(255) DEFAULT NULL,
  `tst_upload` datetime DEFAULT NULL,
  `tst_stuid` int(11) DEFAULT NULL,
  `tst_test_id` int(255) DEFAULT NULL,
  PRIMARY KEY (`tst_id`),
  KEY `table_stu_test_ibfk_1` (`tst_teacher`),
  KEY `table_stu_test_ibfk_2` (`tst_stuid`),
  KEY `table_stu_test_ibfk_3` (`tst_test_id`),
  CONSTRAINT `table_stu_test_ibfk_1` FOREIGN KEY (`tst_teacher`) REFERENCES `table_user` (`u_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `table_stu_test_ibfk_2` FOREIGN KEY (`tst_stuid`) REFERENCES `table_user` (`u_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `table_stu_test_ibfk_3` FOREIGN KEY (`tst_test_id`) REFERENCES `table_test` (`tt_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `table_stu_test` */

insert  into `table_stu_test`(`tst_id`,`tst_test_name`,`tst_term`,`tst_test_type`,`tst_test_second_test`,`tst_teacher`,`tst_upload`,`tst_stuid`,`tst_test_id`) values 
(1,'期末成绩——于健琦','2013-2014学年第二学期','期末成绩	','否',4,'2020-05-14 12:06:56',27,1),
(5,'期末成绩——刘军鹏','2013-2014学年第二学期','期末成绩	','否',2,'2020-05-14 12:06:56',26,1),
(8,'test','test',NULL,NULL,4,NULL,27,15);

/*Table structure for table `table_test` */

DROP TABLE IF EXISTS `table_test`;

CREATE TABLE `table_test` (
  `tt_id` int(255) NOT NULL AUTO_INCREMENT,
  `tt_name` varchar(255) DEFAULT NULL,
  `tt_teacher` int(255) DEFAULT NULL,
  `tt_CreateTime` datetime DEFAULT NULL,
  `tt_UpdateTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `tt_status` int(10) DEFAULT NULL COMMENT '1、正式 2、草稿',
  `tt_course` int(255) DEFAULT NULL,
  `tt_reach` int(255) DEFAULT NULL COMMENT '达标分值',
  PRIMARY KEY (`tt_id`),
  KEY `tt_teacher` (`tt_teacher`),
  KEY `table_test_ibfk_1` (`tt_course`),
  CONSTRAINT `table_test_ibfk_1` FOREIGN KEY (`tt_course`) REFERENCES `table_course` (`tc_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `table_test_ibfk_2` FOREIGN KEY (`tt_teacher`) REFERENCES `table_user` (`u_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `table_test` */

insert  into `table_test`(`tt_id`,`tt_name`,`tt_teacher`,`tt_CreateTime`,`tt_UpdateTime`,`tt_status`,`tt_course`,`tt_reach`) values 
(1,'2016春期末考试',2,'2020-05-14 11:40:13','2020-05-19 09:55:58',2,1,60),
(3,'2016秋期末考试',3,'2020-05-14 11:40:13','2020-05-19 09:55:58',2,3,60),
(4,'2017春期末考试',2,'2020-05-14 11:40:13','2020-05-19 09:55:59',2,4,60),
(5,'2017秋期末考试',4,'2020-05-14 11:40:13','2020-05-19 09:55:59',2,5,60),
(6,'2018春期末考试',5,'2020-05-14 11:40:13','2020-05-19 09:55:59',2,6,60),
(7,'2018秋期末考试',6,'2020-05-14 11:40:13','2020-05-19 09:56:00',2,7,60),
(8,'2019春期末考试',2,'2020-05-14 11:40:13','2020-05-19 09:56:00',2,8,60),
(9,'2019秋期末考试',6,'2020-05-14 11:40:13','2020-05-19 09:56:00',2,9,60),
(10,'2020春期末考试',18,'2020-05-14 11:40:13','2020-05-19 09:56:01',2,10,60),
(11,'2020秋期末考试',19,'2020-05-14 11:40:13','2020-05-19 09:56:02',2,11,60),
(15,'测试添加的数据...',3,'2020-05-17 16:00:00','2020-05-20 10:19:16',2,26,60);

/*Table structure for table `table_test_kp_ip` */

DROP TABLE IF EXISTS `table_test_kp_ip`;

CREATE TABLE `table_test_kp_ip` (
  `ttkp_test_ip` int(255) DEFAULT NULL COMMENT '试卷考题id',
  `ttkp_kp` int(255) DEFAULT NULL COMMENT '试卷知识点',
  `ttkp_ip` int(255) DEFAULT NULL COMMENT '指标',
  `ttkp_weight` int(255) DEFAULT NULL COMMENT '权重',
  KEY `ttkp_ip` (`ttkp_ip`),
  CONSTRAINT `table_test_kp_ip_ibfk_1` FOREIGN KEY (`ttkp_ip`) REFERENCES `table_kp_index_points` (`tip_kp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `table_test_kp_ip` */

insert  into `table_test_kp_ip`(`ttkp_test_ip`,`ttkp_kp`,`ttkp_ip`,`ttkp_weight`) values 
(1,1,1,30),
(1,2,1,30),
(1,3,1,30),
(1,4,1,30),
(1,5,1,30),
(2,6,2,30),
(2,11,2,30),
(2,11,2,30),
(2,11,2,30),
(15,42,10,30);

/*Table structure for table `table_test_stu_score` */

DROP TABLE IF EXISTS `table_test_stu_score`;

CREATE TABLE `table_test_stu_score` (
  `ttss_id` int(255) NOT NULL AUTO_INCREMENT,
  `ttss_title` varchar(255) DEFAULT NULL,
  `ttss_base_score` int(255) DEFAULT NULL,
  `ttss_get_score` int(255) DEFAULT NULL,
  `ttss_test_id` int(255) DEFAULT NULL,
  PRIMARY KEY (`ttss_id`),
  KEY `ttss_test_id` (`ttss_test_id`),
  CONSTRAINT `table_test_stu_score_ibfk_1` FOREIGN KEY (`ttss_test_id`) REFERENCES `table_stu_test` (`tst_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `table_test_stu_score` */

insert  into `table_test_stu_score`(`ttss_id`,`ttss_title`,`ttss_base_score`,`ttss_get_score`,`ttss_test_id`) values 
(1,'一、选择题',10,8,1),
(2,'二、填空题',15,10,1),
(3,'三、简答题',25,15,1),
(4,'四、材料运算',50,35,1),
(5,'填充',55,25,8);

/*Table structure for table `table_user` */

DROP TABLE IF EXISTS `table_user`;

CREATE TABLE `table_user` (
  `u_id` int(255) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(255) DEFAULT NULL,
  `u_nickname` varchar(255) DEFAULT NULL,
  `u_password` varchar(255) DEFAULT NULL,
  `u_rname` varchar(255) DEFAULT NULL,
  `u_tel` varchar(255) DEFAULT NULL,
  `u_email` varchar(255) DEFAULT NULL,
  `u_sex` varchar(1) DEFAULT NULL,
  `u_birthday` datetime DEFAULT NULL,
  `u_adress` text,
  `u_personID` varchar(255) DEFAULT NULL,
  `u_picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `u_stuta` int(10) DEFAULT NULL,
  `u_m_tel` varchar(255) DEFAULT NULL,
  `u_fixed_tel` varchar(255) DEFAULT NULL,
  `u_nation` varchar(10) DEFAULT NULL,
  `u_poli_outlook` varchar(20) DEFAULT NULL,
  `u_native_place` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `u_religious_belief` varchar(10) DEFAULT NULL,
  `CreateTime` datetime DEFAULT NULL,
  `UpdateTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `role_id` int(255) DEFAULT NULL,
  PRIMARY KEY (`u_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `table_user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `table_role` (`role_Id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `table_user` */

insert  into `table_user`(`u_id`,`u_name`,`u_nickname`,`u_password`,`u_rname`,`u_tel`,`u_email`,`u_sex`,`u_birthday`,`u_adress`,`u_personID`,`u_picture`,`u_stuta`,`u_m_tel`,`u_fixed_tel`,`u_nation`,`u_poli_outlook`,`u_native_place`,`u_religious_belief`,`CreateTime`,`UpdateTime`,`role_id`) values 
(2,'教务员','000000000001','123123','教务员	','18899939391','123123@qq.com','女','2020-05-02 16:00:00','伍洲老窖','455444xxx0000001',NULL,1,'022114454','077122213','汉族','党员','北京','道教','2020-05-03 15:24:29','2020-05-13 22:46:38',1),
(3,'王老师','000000000002','123123','王老师','r','r','女','2020-05-04 07:56:17','tr','r','r',1,'r','r','r','r','r','r','2020-05-04 07:56:17','2020-05-16 11:04:01',2),
(4,'教务员4','0000000004','123123','教务员4','18899939392','11155855@qq.com','男','2020-05-04 16:00:00','伍洲老窖','455444xxx0000001',NULL,1,'02211445','sss','qwe','ssww','xxxx','asd','2020-05-05 01:09:36','2020-05-14 22:36:41',1),
(5,'教务员5','000000005','123123','教务员5',NULL,NULL,'女',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,'2020-05-05 01:09:36','2020-05-16 11:04:00',1),
(6,'教务员2','000000012	','123123','教务员2',NULL,NULL,'女',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,'2020-05-05 01:09:36','2020-05-16 11:03:59',1),
(18,'计科教务员','000000023','123123','计科教务员',NULL,NULL,'男',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,'2020-05-05 01:09:36','2020-05-16 11:03:35',1),
(19,'计科教务员2','000000024','123123','计科教务员2',NULL,NULL,'男',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,'2020-05-05 01:09:36','2020-05-16 11:03:32',1),
(20,'教务员3','0000013','123123','教务员3',NULL,NULL,'女',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,'2020-05-05 01:09:36','2020-05-16 11:03:58',1),
(21,'蔡云飞','0001','123123','蔡云飞',NULL,NULL,'男',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,'2020-05-05 01:09:36','2020-05-16 11:03:31',2),
(22,'蔡志成','0002','123123','蔡志成',NULL,NULL,'男',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,'2020-05-05 01:09:36','2020-05-16 11:03:51',2),
(23,'陈强','0003','123123','陈强',NULL,NULL,'男',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,'2020-05-05 01:09:36','2020-05-16 11:03:30',2),
(24,'806550108','0806550108','123123','蔡博宇','18796587410','18796587410@qq.com','男','2020-05-16 11:05:11','广东省化州市下郭街道办坡尾旺跟岭村41号',NULL,NULL,1,'机械工程','机械工程1班',NULL,NULL,NULL,NULL,'2020-05-05 01:09:36','2020-05-20 13:46:11',3),
(25,'906840338','0906840338','123123','谢秉伦','15863698741','18796587410@qq.com','女','2020-05-16 11:05:11','广东省廉江市车板镇大坝村委员会文头岭村96号',NULL,NULL,1,'理论与应用力学','理论与应用力学2班',NULL,NULL,NULL,NULL,'2020-05-05 01:09:36','2020-05-20 13:46:13',3),
(26,'906840425','0906840425','123123','刘军鹏','11478523020','18796587410@qq.com','男','2020-05-16 11:05:11','广东省佛山市沧江路110号3座305',NULL,NULL,1,'心理学','心理学2班',NULL,NULL,NULL,NULL,'2020-05-05 01:09:36','2020-05-20 13:46:07',3),
(27,'906840609','0906840609','123123','于健琦','19998500141','18796587410@qq.com','女','2020-05-16 11:05:11','湖南省东安县南桥镇上塘村6组-37',NULL,NULL,1,'古生物学','古生物学1班',NULL,NULL,NULL,NULL,'2020-05-05 01:09:36','2020-05-20 13:46:10',3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
