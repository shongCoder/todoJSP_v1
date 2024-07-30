# DB
## 테이블
식별번호 : tno(숫자)
todo 제목 : title(문자열)
작성자 : writer(문자열)
작성일자 : regdate(날짜)
최종수정일자 : moddate(날짜)
삭제 여부 : delflag(boolean)

## 쿼리
목록화면 : select / limit(페이징) / order by desc / where delflag
등록화면 : insert
조회화면 : select / where tno
수정/삭제화면
- 삭제 : update / delflag true
- 수정 : update / where tno

- - -

# Java
## VO

## DAO 기능 : dml이면 count가 나와서 성공 여부를 확인해야 함
### 목록 불러오기 : list
- 입력 : 현재 페이지
- 반환 : todo 데이터 목록(List<TodoVO>)
- 쿼리에서 페이징 처리 : 현재 페이지에 따른 offset값 설정

### 등록하기 : insert
- 입력 : TodoVO 객체
- 반환 : tno(리다이렉션을 위해)
- 쿼리 : insert / select(현재 등록한 레코드의 tno)

### 조회하기 : get
- 입력 : tno
- 반환 : Optional<TodoVO>

## 삭제하기 : delete
- 입력 : tno
- 반환 : boolean(성공여부)

## 수정하기 : update
- 입력 : TodoVO 객체
- 반환 : boolean(성공여부)

## 총 데이터 건수 가져오기 : getTotal
- 입력 : 없음
- 반환 : 총 데이터 건수(int)

# 웹 개발
## 컨트롤러 생성
목록 : TodoListController(GET)
- 페이징
등록 : TodoRegisterController(GET/POST)
- 리다이렉션(조회화면 ?tno=)
- 실패한 경우?
조회/수정: TodoGetEditController(GET/POST)
- 수정 시 리다이렉션(조회화면 ?tno=)
- 실패한 경우?
삭제 : TodoDeleteController(POST)
- 리다이렉션(목록화면)
- 실패한 경우? 

# 화면 개발
## JSP
목록화면 : list.jsp
등록화면 : register.jsp
조회화면 : get.jsp
수정/삭제 화면 : edit.jsp

# 추가 개발
조회화면에서 목록화면 가기 -> 보던 페이지로
목록에서 조회로갈 때 링크에 page=${pageInfo.page} 추가
TodoGetEditController에서 page 파라미터 받기
get.jsp에서 page 사용
