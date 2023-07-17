# alter table bookmark
# drop
# foreign key FKkm47dr0i09mor5ks9aaebx15u;
#
# alter table bookmark
# drop
# foreign key FK3ogdxsxa4tx6vndyvpk1fk1am;
#
# alter table heart
# drop
# foreign key FKqyr9v537b0go8pcrr83aq4m4;
#
# alter table heart
# drop
# foreign key FK5pv32bwn1jhofpwouomqupc6u;
#
# alter table hot_topic_answer
# drop
# foreign key FKjbckphptqqx0wc94pr00delr5;
#
# alter table hot_topic_answer
# drop
# foreign key FKl8q4x4hmhm1rq1wqjk9pbhb4h;
#
# alter table nickname
# drop
# foreign key FKdjpf7insbdfpsrfrp29qv4bk8;
#
# alter table post
# drop
# foreign key FK72mt33dhhs48hf9gcqrq4fxte;
#
# alter table post_tag
# drop
# foreign key FKc2auetuvsec0k566l0eyvr9cs;
#
# alter table post_tag
# drop
# foreign key FKac1wdchd2pnur3fl225obmlg0;
#
# alter table product_link
# drop
# foreign key FKoeix04tcevm7ytj49j0kajft0;
#
# alter table view
# drop
# foreign key FKdjs52rs6rvnt6cyl8de7hgero;
#
# alter table view
# drop
# foreign key FK37w6bab99jhjeja56i1te4htp;

drop table if exists bookmark;
drop table if exists feedback;
drop table if exists heart;
drop table if exists hot_topic;
drop table if exists hot_topic_answer;
drop table if exists nickname;
drop table if exists post;
drop table if exists post_tag;
drop table if exists product_link;
drop table if exists tag;
drop table if exists user;
drop table if exists view;

create table bookmark (
                          id bigint not null auto_increment,
                          created_at datetime(6),
                          modified_at datetime(6),
                          post_id bigint,
                          user_id bigint,
                          primary key (id)
) engine=InnoDB;

create table feedback (
                          id bigint not null auto_increment,
                          created_at datetime(6),
                          modified_at datetime(6),
                          content varchar(1000),
                          email varchar(255),
                          primary key (id)
) engine=InnoDB;

create table heart (
                       id bigint not null auto_increment,
                       created_at datetime(6),
                       modified_at datetime(6),
                       post_id bigint,
                       user_id bigint,
                       primary key (id)
) engine=InnoDB;

create table hot_topic (
                           id bigint not null auto_increment,
                           created_at datetime(6),
                           modified_at datetime(6),
                           question varchar(255) not null,
                           temperature_from integer,
                           temperature_to integer,
                           primary key (id)
) engine=InnoDB;

create table hot_topic_answer (
                                  id bigint not null auto_increment,
                                  created_at datetime(6),
                                  modified_at datetime(6),
                                  answer varchar(255),
                                  hot_topic_id bigint,
                                  user_id bigint,
                                  primary key (id)
) engine=InnoDB;

create table nickname (
                          id bigint not null auto_increment,
                          created_at datetime(6),
                          modified_at datetime(6),
                          nickname varchar(255),
                          user_id bigint,
                          primary key (id)
) engine=InnoDB;

create table post (
                      id bigint not null auto_increment,
                      created_at datetime(6),
                      modified_at datetime(6),
                      description varchar(255) not null,
                      sensitivity varchar(255),
                      temperature varchar(255),
                      weather varchar(255),
                      img varchar(255),
                      city varchar(255),
                      district varchar(255),
                      user_id bigint,
                      primary key (id)
) engine=InnoDB;

create table post_tag (
                          id bigint not null auto_increment,
                          created_at datetime(6),
                          modified_at datetime(6),
                          post_id bigint,
                          tag_id bigint,
                          primary key (id)
) engine=InnoDB;

create table product_link (
                              id bigint not null auto_increment,
                              created_at datetime(6),
                              modified_at datetime(6),
                              link text not null,
                              name varchar(30) not null,
                              post_id bigint,
                              primary key (id)
) engine=InnoDB;

create table tag (
                     id bigint not null auto_increment,
                     created_at datetime(6),
                     modified_at datetime(6),
                     name varchar(255),
                     primary key (id)
) engine=InnoDB;

create table user (
                      id bigint not null auto_increment,
                      created_at datetime(6),
                      modified_at datetime(6),
                      name varchar(255),
                      password varchar(255),
                      role varchar(255),
                      username varchar(255),
                      primary key (id)
) engine=InnoDB;

create table view (
                      id bigint not null auto_increment,
                      created_at datetime(6),
                      modified_at datetime(6),
                      post_id bigint,
                      user_id bigint,
                      primary key (id)
) engine=InnoDB;

alter table user
    add constraint UK_sb8bbouer5wak8vyiiy4pf2bx unique (username);

alter table bookmark
    add constraint FKkm47dr0i09mor5ks9aaebx15u
        foreign key (post_id)
            references post (id);

alter table bookmark
    add constraint FK3ogdxsxa4tx6vndyvpk1fk1am
        foreign key (user_id)
            references user (id);

alter table heart
    add constraint FKqyr9v537b0go8pcrr83aq4m4
        foreign key (post_id)
            references post (id);

alter table heart
    add constraint FK5pv32bwn1jhofpwouomqupc6u
        foreign key (user_id)
            references user (id);

alter table hot_topic_answer
    add constraint FKjbckphptqqx0wc94pr00delr5
        foreign key (hot_topic_id)
            references hot_topic (id);

alter table hot_topic_answer
    add constraint FKl8q4x4hmhm1rq1wqjk9pbhb4h
        foreign key (user_id)
            references user (id);

alter table nickname
    add constraint FKdjpf7insbdfpsrfrp29qv4bk8
        foreign key (user_id)
            references user (id);

alter table post
    add constraint FK72mt33dhhs48hf9gcqrq4fxte
        foreign key (user_id)
            references user (id);

alter table post_tag
    add constraint FKc2auetuvsec0k566l0eyvr9cs
        foreign key (post_id)
            references post (id);

alter table post_tag
    add constraint FKac1wdchd2pnur3fl225obmlg0
        foreign key (tag_id)
            references tag (id);

alter table product_link
    add constraint FKoeix04tcevm7ytj49j0kajft0
        foreign key (post_id)
            references post (id);

alter table view
    add constraint FKdjs52rs6rvnt6cyl8de7hgero
        foreign key (post_id)
            references post (id);

alter table view
    add constraint FK37w6bab99jhjeja56i1te4htp
        foreign key (user_id)
            references user (id);
