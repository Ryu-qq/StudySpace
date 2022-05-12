## 개발 환경

Spring boot 2.6.7, java 8, gradle, JPA, H2


## 구현 방법

* 로컬에서 서버가 시작될때 InitDB 클래스에서 테스트 데이터들을 만들어줍니다. 따라서 application.yml에 H2의 datasource의 url만 각자 컴퓨터에 맞게 설정하시면 로컬에서 문제없이 돌아갑니다.
* InitDB 클래스에서 회원을 생성할때 account_id (계정 ID로써 Authentication)를 임의로 생성했습니다. 포스트맨으로 검증이 필요할때 H2 데이터베이스를 확인하고 진행해주세요. 또는 InitDB클래스를   확인해주세요
* 주문 조회, 주문 전체 조회, 주문 등록, 주문 삭제 이렇게 구현하였습니다.
* 테스트 코드를 작성하였습니다. 주문 등록, 삭제 기능을 테스트 코드를 통해 검증 할 수 있습니다. (정상 주문, 주문 수량 초과 검증, 주문 취소 시 아이템 수량 원복)
* 로컬 서버를 가동 시켜놓고 포스트맨으로도 검증을 했습니다.
* HTTP Header 중 Authorization 의 Value Prefix 에 따라 사용자를 구분했습니다. 만약 Authorization 값이 없다면 외부 사용자로 취급합니다.  
  
  
<img width="666" alt="스크린샷 2022-05-12 오후 4 51 58" src="https://user-images.githubusercontent.com/56243159/168019970-43038626-a1f7-4e95-9cea-0cfc85d0fef1.png">  



