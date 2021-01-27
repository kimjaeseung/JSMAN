<template>
  <v-container class="ma-16">
    <div v-for="paper in news" :key="paper.title" class="text-center">
      <h6>
        {{ paper.month }}월 {{ paper.day }}일
        <a href="#" style="color: black">{{ paper.tag1 }}</a
        >, <a href="#" style="color: black">{{ paper.tag2 }}</a
        >, <a href="#" style="color: black">{{ paper.tag3 }}</a>
      </h6>
      <h2>{{ paper.title }}</h2>
      <div>
        <v-btn icon class="flex-collumn-reverse">
          <v-icon>mdi-link-variant</v-icon>
        </v-btn>
        <v-btn icon class="flex-collumn-reverse">
          <v-icon>mdi-bookmark</v-icon>
        </v-btn>
        <v-btn icon class="flex-collumn-reverse">
          <v-icon>mdi-comment-outline</v-icon>
        </v-btn>
        <img width="720" height="480" src="paper.img" alt="이미지" />
      </div>
      <div>
        <p>{{ paper.sub }}</p>
        <p>{{ paper.summary }}</p>
      </div>
      <div v-if="!paper.isSummary">
        <p v-text="paper.main"></p>
      </div>
      <div width="660">
        <a href="#" style="color: black"> #{{ paper.hash1 }}</a
        >, <a href="#" style="color: black"> #{{ paper.hash2 }}</a
        >, <a href="#" style="color: black"> #{{ paper.hash3 }}</a
        >, <a href="#" style="color: black"> #{{ paper.hash4 }}</a
        >, <a href="#" style="color: black"> #{{ paper.hash5 }}</a>
      </div>
      <v-btn
        color="white"
        v-if="paper.isSummary"
        @click="paper.isSummary = !paper.isSummary"
      >
        <v-icon>mdi-chevron-down-circle-outline</v-icon>
        자세히 읽기- {{ paper.time }}초
      </v-btn>
      <v-btn color="white" v-else @click="paper.isSummary = !paper.isSummary">
        <v-icon>mdi-chevron-up-circle-outline</v-icon>
        간단히 읽기
      </v-btn>
      <hr class="ma-16" style="font-size: 80" />
    </div>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    news: [
      {
        month: 1,
        day: 23,
        tag1: '정치',
        tag2: '경제',
        tag3: '사회',
        title: '리포트: 민주주의는 죽지 않았다',
        img: '@/assets/IMG_0377.JPG',
        sub:
          '조 바이든 제46대 미국 대통령이 20일 취임했다. 그는 미국 대선 역사상 가장 많은 표를 얻은 대통령이다. 통합과 치유, 민주주의 회복을 강조한 결과다. 바이든은 취임사에서 모든 국민을 대변하는 대통령이 되겠다고 밝혔다.',
        summary:
          '바이든의 어깨는 무겁다. 코로나19와 경제 위기, 극심한 분열로 망가진 미국을 재건해야 한다. 트럼프 전 대통령이 남긴 미국 우선주의를 지우고 국제 사회에서 협력의 리더십도 회복해야 한다. 바이든 시대의 과제와 달라질 미국을 정리했다.',
        main: `유료 기사 전문은 프라임 멤버만 읽을 수 있습니다. 북저널리즘 프라임 멤버가 되시면 지금 깊이 읽어야 할 다양한 콘텐츠를 무제한 이용할 수 있습니다. 동시에 지적이고 지속 가능한 저널리즘을 지지하는 방법입니다.
        북저널리즘 프라임 멤버가 되시면:
            북저널리즘의 모든 콘텐츠를 무제한 이용할 수 있습니다. 매주 업데이트되는 새 콘텐츠를 만나 보세요. 오직 북저널리즘에서만 읽을 수 있습니다.
            뉴스, 오디오북, 스타트업 플레이북, 뉴룰스 등 프라임 전용 콘텐츠, 프라임 멤버의 지적 여정을 돕는 일대일 컨시어지 서비스를 이용할 수 있습니다.
            온·오프라인으로 에디터와 저자, 다른 멤버들을 만나고 토론할 수 있습니다. 북저널리즘이 개최하는 여러 모임에 우선 초대 및 할인 혜택을 드립니다.
        
        프라임 가입하기: 젊은 혁신가를 위한 콘텐츠 커뮤니티 가입하기`,
        hash1: '미국',
        hash2: '정치',
        hash3: '대선',
        hash4: '외교*국방',
        hash5: '민주주의',
        isSummary: true,
        time: 223,
      },
      {
        month: 1,
        day: 23,
        tag1: '정치',
        tag2: '경제',
        tag3: '사회',
        title: '리포트: 민주주의는 죽지 않았다',
        img: '@/assets/IMG_0448.JPG',
        sub:
          '조 바이든 제46대 미국 대통령이 20일 취임했다. 그는 미국 대선 역사상 가장 많은 표를 얻은 대통령이다. 통합과 치유, 민주주의 회복을 강조한 결과다. 바이든은 취임사에서 모든 국민을 대변하는 대통령이 되겠다고 밝혔다.',
        summary:
          '바이든의 어깨는 무겁다. 코로나19와 경제 위기, 극심한 분열로 망가진 미국을 재건해야 한다. 트럼프 전 대통령이 남긴 미국 우선주의를 지우고 국제 사회에서 협력의 리더십도 회복해야 한다. 바이든 시대의 과제와 달라질 미국을 정리했다.',
        main: `유료 기사 전문은 프라임 멤버만 읽을 수 있습니다. 북저널리즘 프라임 멤버가 되시면 지금 깊이 읽어야 할 다양한 콘텐츠를 무제한 이용할 수 있습니다. 동시에 지적이고 지속 가능한 저널리즘을 지지하는 방법입니다.

북저널리즘 프라임 멤버가 되시면:
북저널리즘의 모든 콘텐츠를 무제한 이용할 수 있습니다. 매주 업데이트되는 새 콘텐츠를 만나 보세요. 오직 북저널리즘에서만 읽을 수 있습니다.
뉴스, 오디오북, 스타트업 플레이북, 뉴룰스 등 프라임 전용 콘텐츠, 프라임 멤버의 지적 여정을 돕는 일대일 컨시어지 서비스를 이용할 수 있습니다.
온·오프라인으로 에디터와 저자, 다른 멤버들을 만나고 토론할 수 있습니다. 북저널리즘이 개최하는 여러 모임에 우선 초대 및 할인 혜택을 드립니다.

프라임 가입하기: 젊은 혁신가를 위한 콘텐츠 커뮤니티 가입하기`,
        hash1: '미국',
        hash2: '정치',
        hash3: '대선',
        hash4: '외교*국방',
        hash5: '민주주의',
        isSummary: true,
        time: 223,
      },
    ],
  }),
};
</script>

<style></style>
