# NEWSHI 뉴하 README


![%5B%E1%84%83%E1%85%A2%E1%84%86%E1%85%AE%E1%86%AB%5D4%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%E1%84%85%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B1%E1%84%92%E1%85%A1%E1%86%AB%20README%E1%84%8C%E1%85%A1%E1%86%A8%E1%84%89%E1%85%A5%E1%86%BC%205ef98b6a2c644b4486b6509a31eb5c72/logo.jpg](README.assets/logo.jpg)

## **👨‍👨‍👧‍👦팀원 소개**

![%5B%E1%84%83%E1%85%A2%E1%84%86%E1%85%AE%E1%86%AB%5D4%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%E1%84%85%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B1%E1%84%92%E1%85%A1%E1%86%AB%20README%E1%84%8C%E1%85%A1%E1%86%A8%E1%84%89%E1%85%A5%E1%86%BC%205ef98b6a2c644b4486b6509a31eb5c72/Untitled.png](README.assets/Untitled.png)

- 천창민 [Github](https://github.com/chunawoos)
- 조성국 [Github](https://github.com/likelionSungGuk)
- 백태훈 [Github](https://github.com/whiteburnout)
- 김재승 **[Github](https://github.com/kimjaeseung)**
- 김재성 [Github](https://github.com/JS-2)
  - [재성님의꿀보이스 '잘자요'](README.assets/honeyvoice.m4a)


## 📑 프로젝트 소개

- **진행기간**: 2021.01.11 ~ 2021.02.19

- **웹사이트 이름**: NewsHi  뉴하

- **목표**
  
    - No스크린샷! No 구글드라이브 공유!
    - URL복사 +붙여넣기만 하면 기사 스크랩 끝!
    - 여러개의 기사도 한 번에 공유할 수 있는 통합 공유링크 생성
    - 지금 나에게 필요한 뉴스를 찾아주는 플랫폼
    - 시간없을 때 알짜배기 기사들만 요약해서 모아 볼 수 있는 플랫폼
    - 기사를 눈으로 읽기 어려운 상황에서 활용할 수 있는 음성으로 읽는 Text To Speech 기능 제공
    
- **슬로건**: '소셜 뉴스 큐레이션 플랫폼'

- **고객여정지도**

    ![%5B%E1%84%83%E1%85%A2%E1%84%86%E1%85%AE%E1%86%AB%5D4%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%E1%84%85%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B1%E1%84%92%E1%85%A1%E1%86%AB%20README%E1%84%8C%E1%85%A1%E1%86%A8%E1%84%89%E1%85%A5%E1%86%BC%205ef98b6a2c644b4486b6509a31eb5c72/1.jpg](README.assets/1.jpg)

    ![%5B%E1%84%83%E1%85%A2%E1%84%86%E1%85%AE%E1%86%AB%5D4%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%E1%84%85%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B1%E1%84%92%E1%85%A1%E1%86%AB%20README%E1%84%8C%E1%85%A1%E1%86%A8%E1%84%89%E1%85%A5%E1%86%BC%205ef98b6a2c644b4486b6509a31eb5c72/2.jpg](README.assets/2.jpg)

- **ER-Diagram**

![ERDiagram](README.assets/ERDiagram.jpg)

- **와이어프레임**

![와이어프레임](README.assets/wireframe.jpg)

## ⚙️ 개발환경

- Vue.js
- Vuetify
- Axios
- Spring Boot
- Maria DB
- Mybatis
- Gradle
- JAVA8(zulu open jdk)

## 🛠️📑 Install / Usage

1. 'newshi' directory로 이동

```bash
cd newshi
```

2. package설치

```bash
$ npm -i
or 
$ yarn -i
```

3. run server

```bash
$ npm serve
or 
$ yarn serve
```



## 기술스택

![Skillset](README.assets/skillset.png)



## 🕹️주요 기능

- **뉴스피드**
    - 구독중인 큐레이터들의 게시 포스트 목록 조회
    - 나의 관심 분야를 태그로 갖는 기사 목록 조회
    - 최신 포스트 조회
    - 내가 좋아할 만한 큐레이터 추천
- **포스트/기사 조회**
    - 기사 본문 조회
    - 기사 요약 조회
    - 큐레이터의 의견 및 요약 조회
    - 기사 본문 소리로 듣기 기능
    - 좋아요/싫어요 기능
    - 나중에 볼 기사 저장 기능
- **채널**
    - 기사 스크랩 기능
    - 큐레이터별 스크랩 내 기사 좋아요/싫어요
    - 큐레이터 구독 기능
    - 커뮤니티 게시판
- **검색**
    - 해시태그 검색 기능
    - 큐레이터 검색 기능
- **마이페이지**
    - 구독중인 큐레이터 목록 조회
    - 프로필 사진 변경 기능
    - 관심 분야 태그 목록 조회 / 수정
- **회원 관리 기능**
    - 회원가입 (카카오톡, 구글)
    - 로그인/로그아웃
    - 비밀번호 찾기(변경)
-  **다크모드 지원**

- **유저의 개선사항 요구 (CS) 메일링 기능 제공**

## 🙈 페이지 소개

[뉴스피드 메인 페이지]

![main](README.assets/main-1613703924841.png)

[뉴스피드 메인 페이지 - 다크모드]

![main_black](README.assets/main_black-1613703924841.png)

[뉴스 상세 페이지]

![Article](README.assets/article-1613703885289.png)

![article_play](README.assets/article_play-1613703885289.png)

![](README.assets/article_save-1613703924841.png)

[저장한 기사 페이지]

![save](README.assets/save-1613703924841.png)

[검색 페이지]

![search](README.assets/search-1613703924841.png)

[포스트 작성 페이지]

![post](README.assets/post-1613703924841.png)

[포스트 성공 후 링크 공유 페이지]

![post_success](README.assets/post_success-1613703924841.png)

![mypage](README.assets/mypage-1613703924841.png)

![tag](README.assets/tag-1613703924841.png)

![mypost](README.assets/mypost-1613703924841.png)

[피드백 메일 작성 페이지]

![feedback](README.assets/feedback-1613703924840.png)

![feedback_success](README.assets/feedback_success-1613703924841.png)

[정책 페이지]

![policy](README.assets/policy-1613703924841.png)

[팀 & 서비스 소개 페이지]

![team](README.assets/team-1613703924841.png)



## 📌 기술 특장점

### **📃 Swagger Hub를 이용한 API 명세서 작성**

Swagger Hub를 이용해 API 명세서를 작성함으로써 FE/BE 협업을 쉽게 할 수 있도록 했습니다. 실제 사용되는 Parameter로 테스트할 수 있고, 어떤 방식으로 데이터를 주고받을지 확인할 수 있어서 개발 시간을 단축하고 불필요한 의사소통 비용을 줄일 수 있었습니다

---

### 🌈 **JIRA를 통한 Task 관리**

 JIRA를 통해 에픽, 스토리, 테스크등을 생성하여 스프린트를 계획하고 팀 전체가 볼  수 있도록 배포하였습니다. 이를 바탕으로 전반적인 팀 업무의 우선순위를 정하고 이에 대해 논의하며 업무를 수행하였습니다. JIRA와 Git lab 그리고 MatterMost를 서로 연동하여 실시간으로 팀원들이 수행한 업무를 확인할 수 있어서 시너지 효과를 발휘할 수 있었습니다.

---

### 📕 **Vuex를 사용한 데이터 관리**

Vuex는 Vue.js 애플리케이션에 대한 상태 관리 패턴 + 라이브러리로써, 컴포넌트 간 지속적으로 사용해야하고, 변경이 용이할 수 있는 데이터를 보관 및 비동기적으로 작업을 처리하기 위해 채택하였습니다.

---

### **📄Notion을 통한 회의록 관리**

노트, 일정, 업무, 데이터, 프로젝트 등을 효율적으로 생성하고 관리할 수 있는 All-in-one 생산성 도구이자 협업 툴입니다. 그래서 프로젝트에 대한 의견 및 회의록을 공동 작업하기 위해 채택하였습니다. 
매주 새로운 워크스페이스를 만들고, 워크스페이스 내에서 일일 스크럼, 앞으로의 일정, 기타 배운점 및 서로의 아이디어를 공유하여 팀원 간 의견을 편히 나눌 수 있었습니다.

---