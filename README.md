# be-chess

2025 마스터즈 체스 프로젝트

## 주의할 점

- Lucas 컨텐츠의 내용을 공개된 저장소 또는 웹에 올리는 것은 엄격하게 금지합니다.
- 커밋메시지는 한글로 두 줄 이상 작성합니다.
- MVC 패턴은 사용하지 않습니다.

## Step 2 - 1

### 체스 프로젝트 시작

- [x] PawnTest 클래스 생성
  - 매개변수 생성자를 이용해 Pawn 생성
  - 생성된 Pawn 이 매개변수로 넣은 값이 잘 들어갔는지 확인
- [x] Pawn 클래스 생성
  - Pawn 의 생성자를 만들어 매개변수로 받은 값을 저장
- [x] refactoring
  - `create()` 메소드 내에 중복된 코드를 메소드로 추출

## Step 2 - 2

### 체스판 생성

- [x] `Pawn class` 클래스 변경
  - `default constructor` 추가
  - `white`와 `black`을 **상수**로 변경
- [x] `BoardTest class` 추가
  - `Board` 를 생성
  - `board` 안에 `Pawn`을 추가 시키고 현재 `piece`가 몇 개인지 확인
  - `board` 안에 `piece`가 잘 들어갔는지 확인
- [x] 패키지 분리
  - `chess` 패키지 생성
  - `chess` 패키지 안에 `piece` 패키지 생성
- [x] extra test
  -  `Board`안에 `Pawn` 외의 객체 생성시 예외 처리

## Step 2 - 3

### 체스판 초기화

- [x] `Board` 클래스 기능 추가
  - `initialize(): void` 메소드 추가
    - `Board`에 `Pawn`을 초기화
    - 검은색 `Pawn`은 대문자 `P`로 표시
    - 흰색 `Pawn`은 소문자 `p`로 표시
  - `print(): void` 메소드 추가
    - `Board`에 있는 `Pawn`을 출력
- [x] `BoardTest` 클래스 추가
  - `initialize` 메소드 테스트
  - `print` 메소드 테스트
- [x] `main()` 메소드를 갖는 클래스 추가
  - `Scanner`를 이용해 사용자로부터 입력을 받음
    - `start` 입력시 체스 게임 시작하고 **현재 상태**를 출력
    - `end` 입력시 체스 게임 종료
    - `while`문을 이용해 반복

## Step 2 - 4

### 모든 기물 배치하기

- [x] `\n` 코드 리펙토링
  - `StringUtils` 클래스 생성
  - `StringUtils` 클래스 안에 `NEWLINE` 상수 추가
  - `System.getProperty("line.separator")`를 이용해 `\n` 대신 `NEWLINE` 사용
  - `private constructor` 추가
- [x] `Pawn` 클래스 rename
  - `Pawn` 클래스를 `Piece`로 변경
  - `color`, `name` 필드 추가
  - 모든 필드에 `final` 추가
  - `Piece` 생성자를 팩토리 메소드로 변경
    - _ex_
      - `Piece.createWhitePawn()`
      - `Piece.createBlackPawn()`
      - `Piece.createWhiteRook()` ...
  - 검은 말과 흰 말을 구분하는 메소드 추
- [x] `PieceTest`
  - 여러 `Piece`를 생성하고 `color`, `name`이 잘 들어갔는지 확인
- [x] `Board` 클래스 변경
  - `initialize()` 메소드를 이용해 모든 `Piece`를 초기화

## Step 2 - 5

### 기물 위치 부여 및 점수계산

- [x] `Piece` 클래스 enum 추가
  - `Color` enum 추가
    - `WHITE`, `BLACK`, `NOCOLOR` 추가
  - `Type` enum 추가
    - `PAWN`, `ROOK`, `KNIGHT`, `BISHOP`, `QUEEN`, `KING`, `NO_PIECE` 추가
    - 식별 문자 함께 관리
    - 식별 문자는 **소문자**로만 관리
  - 기존 **상수 값 제거**
- [x] `Piece` 클래스 `enum` 이용한 팩토리 메소드 리펙토링
  - `REPRESANTATION` 을 `Type` enum으로 변경
  - `createWhite(type: Type): Piece`, `createBlack(type: Type): Piece` 메소드 통합 관리
- [x] `Board` 클래스 변경
  - `initialize()` 메소드에서 빈 공간도 `Piece.createBlank()`로 초기화
  - `Rank(row)` 를 기반으로 `List<Piece>` 관리 로직 수정
- [x] 기물과 색에 해당하는 **기물의 개수**를 반환
  - `Board` 클래스에 `countPieceByColorAndType(color: Color, type: Type): int` 메소드 추가
  - _ex_
    - 검은 폰의 개수: `countPieceByColorAndType(Color.BLACK, Type.PAWN): int`
- [x] 주어진 위치의 기물 조회 로직 작성
  - `Board` 클래스에 `findPieceByPosition(position : String): Piece` 메소드 추가
  - `row`는 `1~8`, `column`은 `a~h`로 관리
  - 좌측 상단이 `(8, a)`, 우측 하단이 `(1, h)`로 관리
  - _ex_
    - `findPieceByPosition("a1"): Piece`
- [x] 임의의 기물을 **체스판 위에 추가**
  - `Board` 클래스에 `move(to : String, piece : Piece): void` 메소드 추가
  - test 하기 위해서 **빈 체스판** 생성 후 `move` 메소드로 기물 추가
  - 기존 `List<Piece>` 에서 제거 후 새로운 Piece 로 변경하는 경우 `set()` 메소드 사용
- [x] **점수 계산** 로직 추가
  - `Board` 클래스에 `calculatePoint(color: Color): double` 메소드 추가
  - 각 기물의 점수는 다음과 같음
    - 폰: **1.0**
    - 룩: **5.0**
    - 나이트: **2.5**
    - 비숍: **3.0**
    - 퀸: **9.0**
    - 킹: **0.0**
  - _ex_
    - 흰 폰의 점수: `calculateScore(Color.WHITE): double`
  - 한 번에 **한 쪽의 점수만** 계산
  - 검은 색과 흰 색 기물을 구분해서 **점수가 높은 순서로 정렬**한다
  - 각 색에 해당하는 모든 기물을 **`Collection`**에 담아서 **정렬**한다
- [x] 리펙토링
  - **인터페이스**를 이용해 추출

## Step 2 - 6

### 기물 이동 구현

- [x] `Board` 클래스에 `move(from : String, to : String): void` 메소드 추가
  - `from` 위치의 기물을 `to` 위치로 이동
- [x] `BoardTest` 클래스 추가
  - `move` 메소드 테스트
- [x] `main()`문 변경
  - `move` 입력시 `from`, `to` 위치를 입력 받아 `Board` 클래스의 `move` 메소드 호출
  - `move` 입력시 이동한 후의 **현재 상태**를 출력
  - `String,startsWith()` 메소드 활용
  - _ex_
    - `> move a2 a4`
- [x] `Board` 클래스를 **역할을 구분하고 역할에 따라 새로운 클래스를 추가**한다
  - `Board` 클래스는 **체스판의 상태**를 관리
  - `Game` 클래스는 **게임의 진행**을 관리
  - 출력하는 로직도 `View` 클래스로 분리
- [x] 기물 이동
  - 이동할 때 **기물이 없는 경우** 예외 처리
  - 이동할 때 **기물이 있는 경우** 예외 처리
  - 이동할 때 **기물이 같은 색인 경우** 예외 처리
  - 이동할 때 **기물이 다른 색인 경우** 기존 기물을 제거하고 새로운 기물로 변경
  - `King` 기물 이동 구현
    - `King` 기물은 **상하좌우 대각선**으로 **한 칸** 이동 가능
  - `Queen` 기물 이동 구현
    - `Queen` 기물은 **상하좌우 대각선**으로 **여러 칸** 이동 가능
- [x] **다형성**을 고려한다
  - `Piece`클래스를 상속하는 `King`, `Queen` 클래스를 추가
  - 기물의 이동 가능여부를 위해 `Enum`추가도 고려
