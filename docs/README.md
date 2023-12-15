# 기능 명세

- 기본적으로 1부터 9까지 서로 다른 수로 이루어진 3자리의 수를 맞추는 게임이다.

## 도메인
### model
- #### SingleNumber
  - 입력 받은 값을 통해 단일 숫자 객체를 생성한다.
    - 입력 받은 값을 검증 한다.
      - [x] 제한 범위 내에 포함되는 값이 아니라면 `IllegalArgumentException` 예외 처리 

- #### Answer
  - 입력 받은 값을 통해 정답 객체를 생성한다.
    - 입력 받은 값을 검증 한다.
      - [x] 개수 제한 조건을 충족하지 않으면 `IllegalArgumentException` 예외 처리
      - [x] 중복된 숫자가 존재하면 `IllegalArgumentException` 예외 처리

  - 입력된 정답과 컴퓨터의 정답의 비교 결과를 반환한다.
    - 입력된 정답과 컴퓨터의 정답을 비교한다.
      - [x] 같은 자리에 위치한 같은 값의 개수를 센다.
      - [x] 다른 자리에 위치한 같은 값의 개수를 센다.

---
## 어플리케이션 서비스
- ## 비즈니스

### handler
- ### InputHandler
- ### 정답 입력
  - [x] 정답 객체를 생성할 단일 숫자 객체로 변환 
    - 입력 값 검증
      - [x] null 이면 `IllegalArgumentException` 예외 처리
      - [x] 세자리 숫자(0이 포함되지 않은)로만 이루어진 값이 아니라면 `IllegalArgumentException` 예외 처리
    
- ### 재시도 입력
  - [x] 재시도 입력 결과 반환
    - 입력 값 검증
      - [x] null 이면 `IllegalArgumentException` 예외 처리
      - [x] 값이 1 또는 2 인 한 자리 숫자로만 이루어진 값이 아니라면 `IllegalArgumentException` 예외 처리 

### generator
- ### SingleNumberGenerator
  - [x] 1-9 사이에 랜덤한 단일 숫자 객체 1개를 생성한다.

- ### AnswerGenerator
  - [x] SingleNumberGenerator 를 통해 자동 정답을 생성한다.
    - [x] 중복되지 않는 단일 숫자 객체 3개를 생성한다.
    - [x] 생성된 객체 리스트를 통해 정답 객체를 생성한다.

### controller
- ### BaseballController
- [x] 게임 시작
- [x] 재시도 입력 값에 따라 재시작 혹은 종료

- ## UI (View)
- #### InputView
  - [x] 서로 다른 3자리의 수를 입력 받는다.
  - [x] 게임이 끝난 경우 재시작/종료를 구분하는 1과 2 중 하나의 수를 입력 받는다.

- #### OutputView
  - [x] 게임 시작 안내문 출력 
  - [x] 입력한 수에 대한 결과를 볼, 스트라이크 개수로 표시
  - [x] 게임 종료 안내문 출력