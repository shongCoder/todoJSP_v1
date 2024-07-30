package org.example.w2.todo;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TodoVO {

    private Integer tno;
    private String title;
    private String writer;
    private Timestamp regDate;
    private Timestamp modDate;
    private boolean delFlag;

}
