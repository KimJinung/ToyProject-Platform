1. Design
2. Impl
3. Refactoring
4. Monitoring

# ToyProject-Platform
## Goal
이커머스 플랫폼을 제로 베이스부터 구축 해본다. 

아키텍처를 먼저 생각하는 것이 아니라 비지니스를 위한 기능을 먼저 생각한다. 
기능이 점차 추가됨에 따라 리팩터링한다.


# 첫 번째 목표
1. 회원 가입을 할 수 있다.
2. 로그인을 할 수 있다.
3. 상품을 등록 및 취소할 수 있다.
4. 상품을 주문할 수 있다.

회원은 uuid를 pk로 사용해서 구분한다. username과 password 그리고 address라는 기본적인 정보만 가진다.
상품은 itemName과 price 그리고 stock이라는 기본적인 정보만 가진다.
주문은 주문에 대한 고유한 id를 가진다. 주문자에 대한 정보를 가진다. (주문자 이름, 배송주소)
아이템에 대한 정보를 가진다.(itemName, quantity)
총 주문에 대한 합계 금액을 가진다.

회원은 uuid를 통해 구분한다. username, password, address 정보만 가진다.
아이템은 itemId를 통해 구분한다. itemName, price, stock에 대한 정보만 가진다.
주문은 orderId를 통해 구분한다. userId(회원의 uuid)를 fk로 가진다. itemId를 pk로 가진다.
각 주문에 대한 수신자 이름과 주소는 변경할 수 있고, 주어지지 않는 경우 회원 정보를 따라간다.

![img_1.png](img_1.png)

### Member
회원은 uuid로 구분한다. username, password, address, orderList를 가진 객체다.
주문은 어떤 member가 주문했는지, 어떤 아이템들을 주문했는지, 그리고 주문 날짜와 주문 상태, 배송 상태를 처리하는 객체다.
orderLineItem은 주문한 아이템에 대한 수량과 가격 정보를 처리하는 객체다.
item은 상품을 처리하는 객체다. id로 구분한다. 이름, 가격, 재고수량, 카테고리를 가진다.
카테고리는 id로 구분하며 이름을 가진다. 부모 객체와 자식 카테고리를 가진다. (순환 참조 안생기게 주의)
배송 클래스는 값 타입이다.

---
domain: 도메인 모델을 정의한다.
infrastructure: db 및 외부 API 연결을 담당한다.
usecase: 비지니스 로직
web: 웹 레이어