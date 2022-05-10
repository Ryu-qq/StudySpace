## 개발 환경

Spring boot 2.6.7, java 8, gradle, JPA, H2


## 구현 방법

* 프로젝트가 시작될때 InitDB 클래스에서 테스트 데이터들을 만들어줍니다. 따라서 application.yml에 H2의 datasource의 url만 각자 컴퓨터에 맞게 설정하시면 로컬에서 문제없이 돌아갑니다.
* InitDB 클래스에서 회원을 생성할때 account_id (계정 ID로써 Authentication)를 임의로 생성했습니다.
* 요구사항에 테스트 코드 작성이 포함되어 있지 않아 테스트코드를 작성하지 않고 각각의 경우를 포스트맨으로 구현, 검증하였습니다.


## 기능 요구사항 검증

### 1.임대인, 임차인, 공인중개사는 커뮤니티에 글을 쓸 수 있고, 외부 사용자는 글을 쓸 수 없습니다.  (구현)

<img width="1072" alt="스크린샷 2022-05-10 오후 11 01 56" src="https://user-images.githubusercontent.com/56243159/167648129-63dd6d8b-ad39-416d-8c58-a885439939f6.png">  

<img width="1072" alt="스크린샷 2022-05-10 오후 11 02 30" src="https://user-images.githubusercontent.com/56243159/167648171-4b5833c8-c06d-4f16-ad2b-8d6956075a3d.png">  

### 2.글 목록에선 작성한 사용자가 어떤 계정 타입인지를 표시할 수 있어야 합니다. ex ) 김씨(공인중개사). 계정 타입은 한글로 표시되어야 합니다.  (구현)

<img width="1072" alt="스크린샷 2022-05-10 오후 11 09 53" src="https://user-images.githubusercontent.com/56243159/167648939-a848c5c0-a361-4168-8df5-4199e1f2b3a9.png">  


### 3.커뮤니티에 가입한 사용자라면 글 목록에 자신이 좋아요한 글인지 아닌지를 표시해줄 수 있어야 합니다. (구현)

### 전체 게시물 목록에서 로그인한 사용자가 좋아요한 게시물들에 likeStatus라는 필드로 정보들을 넣었습니다.

<img width="1072" alt="스크린샷 2022-05-10 오후 11 11 30" src="https://user-images.githubusercontent.com/56243159/167649251-88fcc61f-9166-422a-916a-ec96689004a0.png">  


### 4.글 목록에는 글에 달린 좋아요 수를 표시할 수 있어야 합니다.  (구현)

### likeCount로 글 목록에 좋아요 수를 표시했습니다.

<img width="1072" alt="스크린샷 2022-05-10 오후 11 15 58" src="https://user-images.githubusercontent.com/56243159/167650181-663d9d72-d801-4fcb-962f-eb399c474812.png">  


## 기술 요구사항 검증

### 1. 글 작성, 수정, 삭제

### 글 작성 (구현)
<img width="1072" alt="스크린샷 2022-05-10 오후 11 29 47" src="https://user-images.githubusercontent.com/56243159/167653236-3f1bf458-81e6-4d3e-89a7-e27139f248af.png">  

<img width="1072" alt="스크린샷 2022-05-10 오후 11 29 55" src="https://user-images.githubusercontent.com/56243159/167653242-513aa76d-fe39-4b03-aedf-03370bb06b9b.png">  




### 글 수정 (구현)
<img width="1072" alt="스크린샷 2022-05-10 오후 11 19 54" src="https://user-images.githubusercontent.com/56243159/167651447-9eca4519-bdcb-475e-bf23-707bdcf68938.png">  

<img width="1072" alt="스크린샷 2022-05-10 오후 11 20 04" src="https://user-images.githubusercontent.com/56243159/167651566-344bc7c2-a8f8-462f-a71f-e916e3ed25aa.png">  


### 글 삭제 (구현)

<img width="1072" alt="스크린샷 2022-05-10 오후 11 20 17" src="https://user-images.githubusercontent.com/56243159/167651683-557df558-056f-4f1f-8096-f92343ba599d.png">  

<img width="1072" alt="스크린샷 2022-05-10 오후 11 20 26" src="https://user-images.githubusercontent.com/56243159/167651768-67f9b7c5-4dd1-4900-86a3-91eaba2f3d66.png">  


### 2. 어떤 사용자가 어떤 글에 좋아요 했는지 히스토리를 확인할 수 있어야 합니다. (구현)

게시물을 단건으로 조회 했을때 likewho 필드에 정보를 넣었습니다.


  <img width="1072" alt="스크린샷 2022-05-10 오후 11 35 56" src="https://user-images.githubusercontent.com/56243159/167654700-233158a9-4701-44a7-9db2-805800c01fd2.png">  



### 3.글에 좋아요는 한 계정이 한 글에 한 번만 할 수 있습니다.

로그인한 사용자만 좋아요 기능을 사용할 수 있게 했고 게시물이 좋아요 상태가 아니라면 좋아요를, 좋아요 상태라면 좋아요를 취소하는 로직을 작성하였습니다. (PostController 참고)

### 4. 각 글은 작성시간, 마지막 수정시간, 삭제시간의 대한 히스토리를 확인할 수 있어야 합니다. (구현)

글을 조회 및 수정 삭제 후 h2 콘솔 창에 게시물 조회 시 modified_date 변경 확인함



### 5. 회원 탈퇴한 사람이 썻던 글이나 글이 삭제 되었을때 전체조회, 단건 조회, 수정, 삭제를 불가능하게 하는 기능을 필요하다 생각하여 구현했습니다.

단건 조회 사진만 첨부
##### 삭제한 게시물 단건 조회
   <img width="834" alt="스크린샷 2022-05-10 오후 11 44 32" src="https://user-images.githubusercontent.com/56243159/167656764-77d34db9-cfca-40ce-8777-93445a7a1742.png">  


  <img width="662" alt="스크린샷 2022-05-10 오후 11 44 42" src="https://user-images.githubusercontent.com/56243159/167656858-046a17eb-dc09-4311-9ac9-8224f8a1ae7b.png">



##### 탈퇴한 회원의 게시물 단건 조회
<img width="399" alt="스크린샷 2022-05-10 오후 11 44 56" src="https://user-images.githubusercontent.com/56243159/167656956-a314a7cd-be21-402b-b236-c3df20eb4d13.png">


<img width="657" alt="스크린샷 2022-05-10 오후 11 48 38" src="https://user-images.githubusercontent.com/56243159/167657115-6974b838-655e-4d66-939a-093dfcbdc1a0.png">




프로젝트 구성
========

