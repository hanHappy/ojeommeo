import globals from "globals";
import tseslint from "typescript-eslint";
import pluginVue from "eslint-plugin-vue";
import tsEslintParser from "@typescript-eslint/parser";
import stylistic from "@stylistic/eslint-plugin";

export default [
  { files: ["**/*.{js,ts,vue}"] },
  {
    ignores: [
      "**/*.config.*",
      "**/.output/",
      "**/.nuxt/",
      "./plugins/",
      "**/middleware/",
    ],
  },
  {
    languageOptions: {
      globals: {
        ...globals.browser,
        ...globals.node,
        ...globals.es2022,
        ...globals.commonjs,
      },
      parserOptions: {
        parser: tsEslintParser,
        ecmaFeatures: { modules: true },
        ecmaVersion: "latest",
      },
    },
  },
  ...tseslint.configs.recommended,
  ...pluginVue.configs["flat/recommended"],
  {
    rules: {
      "no-unused-vars": "off",
      "@typescript-eslint/no-unused-vars": "warn",
      "@typescript-eslint/explicit-function-return-type": "off",
      "@typescript-eslint/no-explicit-any": "warn",
      "vue/multi-word-component-names": "off",
      "vue/no-use-v-if-with-v-for": "warn",
    },
  },
  {
    plugins: {
      "@stylistic": stylistic,
    },
    rules: {
      // 탭 크기 2
      "@stylistic/indent": ["error", 2],
      // 화살표 함수 괄호 여부 - 필요시 생성
      "@stylistic/arrow-parens": ["error", "as-needed"],
      // 화살표 함수 공백 여부 - 항상
      "@stylistic/arrow-spacing": "error",
      // 배열 앞 뒤 공백 여부 - 항상
      "@stylistic/array-bracket-spacing": ["error", "always"],
      // 코드 블럭 내부 공백 여부 - 항상
      "@stylistic/block-spacing": "error",
      // 중괄호 스타일 - 여는 괄호가 선언과 같은 줄에 배치 (1tbs)
      "@stylistic/brace-style": "error",
      // 객체 속성 후행 쉼표 - 닫는 괄호와 같은 라인에 있지 않으면 무조건 붙이기
      "@stylistic/comma-dangle": ["error", "always-multiline"],
      // 쉼표 공백 여부 - 쉼표 전엔 없고 쉼표 뒤엔 필수
      "@stylistic/comma-spacing": "error",
      // 객체 속성 공백 여부 - 콜론 전엔 없고 콜론 뒤엔 필수
      "@stylistic/key-spacing": "error",
      // 키워드 공백 여부 - 키워드 앞 뒤로 공백 필수
      "@stylistic/keyword-spacing": "error",
      // 코드 최대 길이 설정
      "@stylistic/max-len": [
        "error",
        {
          // 한 줄에 최대 120자
          code: 120,
          // 주석 제외
          ignoreComments: true,
          // URL 제외
          ignoreUrls: true,
          // 문자열 제외
          ignoreStrings: true,
          // 정규식 제외
          ignoreRegExpLiterals: true,
          // 템플릿 문자열 제외
          ignoreTemplateLiterals: true,
        },
      ],
      // 인터페이스 / 타입 정의 시 멤버 뒤에 붙을 구분자 - 세미콜론 (타입스크립트 권장 사항)
      "@stylistic/member-delimiter-style": "error",
      // 불필요한 세미콜론 - 비허용
      "@stylistic/no-extra-semi": "error",
      // 중복 공백 - 비허용
      "@stylistic/no-multi-spaces": "error",
      // 객체와 속성 사이 공백 - 비허용
      "@stylistic/no-whitespace-before-property": "error",
      // 중괄호 내부 공백 - 단일 라인, 속성 값이 있을 경우 시작과 끝에 공백
      "@stylistic/object-curly-spacing": ["error", "always"],
      // 객체 속성값 따옴표 여부 - 한 속성이라도 따옴표가 필요할 경우 모든 속성에 따옴표, 그렇지 않을 경우 따옴표 없음
      "@stylistic/quote-props": ["error", "consistent-as-needed"],
      // 따옴표 - 작은 따옴표
      "@stylistic/quotes": ["error", "single"],
      // 세미콜론 - 문장 끝마다 세미콜론 삽입
      "@stylistic/semi": "error",
      // 코드 블럭 시작 전 공백 여부 - 항상
      "@stylistic/space-before-blocks": "error",
      // 함수와 매개변수 괄호 사이 공백 여부 - 항상
      "@stylistic/space-before-function-paren": "error",
      // 중위 연산자 공백 여부 - 항상
      "@stylistic/space-infix-ops": "error",
      // 단항 연산자 공백 여부 - new, delete, typeof, void,yield: 공백 필수, -, +, --, ++, !,!!: 공백 제외
      "@stylistic/space-unary-ops": "error",
      // 스위치 case문 공백 여부 - 콜론 앞 공백 제외, 콜론 뒤 공백 필수
      "@stylistic/switch-colon-spacing": "error",
    },
  },
];
