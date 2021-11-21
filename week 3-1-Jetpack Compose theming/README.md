# [Jetpack Compose theming](https://developer.android.com/codelabs/jetpack-compose-theming?authuser=4&continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fcompose%3Fhl%3Den%26authuser%3D4%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fjetpack-compose-theming&hl=en#0)
> 이 코드랩에서는 Jetpack Compose의 테마 API를 사용하여 애플리케이션의 스타일을 지정하는 방법을 배웁니다.
Light, Dark 테마와 같은 여러 테마를 지원하여 애플리케이션 전체에서 일관되게 사용되도록 색상, 모양 및 타이포그래피를 사용자 지정하는 방법을 살펴보겠습니다.

**무엇을 배울 것인가**
- 머티리얼 디자인 입문서 및 브랜드에 맞게 사용자 정의하는 방법
- Compose가 머티리얼 디자인 시스템을 구현하는 방법
- 앱 전체에서 색상, 타이포그래피 및 모양을 정의하고 사용하는 방법
- 구성 요소 스타일 지정 방법
- Light, Dark 테마를 지원하는 방법

## 3. [Material Theming](https://material.io/design/introduction#principles)
> Jetpack Compose는 디지털 인터페이스를 만들기 위한 종합적인 디자인 시스템인 Material Design의 구현을 제공합니다.
머티리얼 디자인 구성 요소(버튼, 카드, 스위치 등)는 머티리얼 테마를 기반으로 구축되어 머티리얼 디자인을 사용자 지정하여 제품 브랜드를 더 잘 반영할 수 있는 체계적인 방법입니다.
머티리얼 테마는 색상, 타이포그래피 및 모양 속성으로 구성됩니다. 이를 사용자 정의하면 앱을 빌드하는 데 사용하는 구성 요소에 자동으로 반영됩니다.

## 4. Define your theme

### MaterialTheme
> Jetpack Compose에서 테마를 구현하기 위한 핵심 요소는 MaterialTheme composable입니다. \
  이 컴포저블을 작성 계층에 배치하면 그 안의 모든 구성 요소에 대한 색상, 유형 및 모양에 대한 사용자 정의를 지정할 수 있습니다.

```kotlin
@Composable
fun MaterialTheme(
    colors: Colors,
    typography: Typography,
    shapes: Shapes,
    content: @Composable () -> Unit
) { ...
```

### Create a Theme
> 스타일을 중앙 집중화하려면 MaterialTheme를 래핑하고 구성하는 자신만의 컴포저블을 만드는 것이 좋습니다. \
  이렇게 하면 테마 사용자 지정을 지정할 수 있는 단일 위치가 제공되며 여러 곳에서 쉽게 재사용할 수 있습니다. \
  여러 화면 또는 @Previews에서 예를 들어 앱의 여러 섹션에 대해 다른 스타일을 지원하려는 경우와 같이 필요한 경우 여러 테마 컴포저블을 만들 수 있습니다.

### [Colors](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html?authuser=4)
> Compose의 색상은 Color 클래스를 사용하여 정의됩니다. 색상을 ULong으로 지정하거나 별도의 색상 채널로 지정할 수 있는 여러 생성자가 있습니다.

- 색상 지정을 위한 일반적인 '#dd0d3c' 형식에서 변환하려면 '#'을 '0xff', 즉 Color(0xffdd0d3c)로 바꾸십시오. 여기서 'ff'는 전체 알파를 의미합니다.

### [Typography](https://developer.android.com/reference/kotlin/androidx/compose/material/Typography?authuser=4)
> Compose에서 TextStyle 개체를 정의하여 일부 텍스트의 스타일을 지정하는 데 필요한 정보를 정의할 수 있습니다.

- [TextStyle](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/TextStyle?authuser=4)

### Shapes
> 앱에서 모양을 사용하여 브랜드를 표현하고 싶습니다.

- Compose는 모양 테마를 정의하는 데 사용할 수 있는 [RoundedCornerShape](https://developer.android.com/reference/kotlin/androidx/compose/foundation/shape/RoundedCornerShape?authuser=4) 및 [CutCornerShape](https://developer.android.com/reference/kotlin/androidx/compose/foundation/shape/CutCornerShape?authuser=4) 클래스를 제공합니다.

### [Dark Theme](https://developer.android.com/guide/topics/ui/look-and-feel/darktheme?authuser=4)
> 앱에서 어두운 테마를 지원하면 앱이 사용자의 기기(Android 10부터 전역 어두운 테마 토글이 있음)에 더 잘 통합될 뿐만 아니라 전력 사용량을 줄이고 접근성 요구 사항을 지원할 수 있습니다.
Material은 [어두운 테마 생성에 대한 디자인 지침](https://material.io/design/color/dark-theme.html#usage)을 제공합니다.


# ComposeFest2021
2021 DevFest ComposeFest 코드랩 Repo 입니다
본 폴더를 Android Studio를 이용해서 열어주세요.
작업을 완료하고, push 해주세요.

# Jetpack Compose Theming Codelab

This folder contains the source code for the [Jetpack Compose Theming codelab](https://developer.android.com/codelabs/jetpack-compose-theming).

In this codelab you will learn how to use [Jetpack Compose](https://developer.android.com/jetpack/compose)’s theming APIs to style your application. We’ll see how to customize colors, shapes and typography so that they’re used consistently throughout your application, supporting multiple themes such as light & dark theme.

## Screenshots

![Start state](screenshots/start.png "Before: unstyled app")
![Finish state, light](screenshots/finish_light.png "After: styled app")
![Finish state, dark](screenshots/finish_dark.png "After: dark theme")

## License

```
Copyright 2020 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
