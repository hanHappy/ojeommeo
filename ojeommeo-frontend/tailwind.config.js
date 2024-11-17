/** @type {import('tailwindcss').Config} */
export default {
  content: [],
  darkMode: 'class',
  theme: {
    extend: {},
    colors: {
      primary: {
        mute: '#FEF8DA',
        light: '#FEE290',
        DEFAULT: '#ed702d',
        dark: '#f3b72d',
        'dark-mute': '#2e3848',
        'dark-light': '#778192',
        'dark-DEFAULT': '#E8EDF3',
      },
      wallpaper: {
        DEFAULT: '#FFFFFF',
        dark: '#0d1826',
      },
      disabled: {
        DEFAULT: '#EEEEEE',
      },
      text: {
        base: {
          DEFAULT: '#333333', // 라이트모드 메인 텍스트
          dark: '#E8EDF3', // 다크모드 메인 텍스트
        },
        light: {
          DEFAULT: '#666666', // 라이트모드 중간 텍스트
          dark: '#B8C4D0', // 다크모드 중간 텍스트
        },
        mute: {
          DEFAULT: '#999999', // 라이트모드 약한 텍스트
          dark: '#8896A3', // 다크모드 약한 텍스트
        },
      },
      borderColor: {
        DEFAULT: '#d0d6dd',
      },
      borderRadius: {
        DEFAULT: '8px',
      },
      overlay: {
        light: {
          1: '#00000005',
          2: '#0000000a',
          3: '#00000014',
        },
        dark: {
          1: '#ffffff14',
          2: '#ffffff20',
          3: '#ffffff39',
        },
      },
    },
    container: {
      center: true,
      padding: '2rem',
      screens: {
        sm: '640px', // @media (min-width: 640px)
        md: '768px', // @media (min-width: 768px)
        lg: '1024px', // @media (min-width: 1024px)
      },
    },
  },
  plugins: [],
  purge: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
}
