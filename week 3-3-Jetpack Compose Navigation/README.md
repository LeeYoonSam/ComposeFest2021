# Jetpack Compose Navigation

## 소개

### Navigating with Compose
> Navigation은 특정 경로를 따라 앱 내의 한 대상에서 다른 대상으로 탐색할 수 있게 해주는 Jetpack 라이브러리입니다.
Navigation 라이브러리는 Jetpack Compose로 구축된 화면 내에서 일관되고 관용적인 탐색을 가능하게 하는 특정 아티팩트를 제공합니다.

### What you'll do
> [Rally Material](https://material.io/design/material-studies/rally.html#about-rally) 연구를 이 코드랩의 기반으로 사용할 것입니다. \
  기존 Navigation 코드를 마이그레이션하여 Jetpack Navigation Component를 사용하여 Jetpack Compose의 화면 간을 탐색합니다.

### What you'll learn
- Jetpack Compose와 함께 Jetpack Navigation 사용의 기본 사항
- 컴포저블 간 탐색
- 필수 및 선택적 인수로 탐색
- 딥 링크를 사용하여 탐색
- 탐색 계층 구조에 TabBar 통합
- 탐색 테스트

## 3. Migrate to Navigation
> Rally는 처음에는 내비게이션을 사용하지 않는 기존 앱입니다. 마이그레이션은 여러 단계를 따릅니다.

1. 탐색 종속성 추가
2. NavController 및 NavHost 설정
3. 목적지에 대한 경로 준비
4. 원래 목적지 메커니즘을 탐색 경로로 교체


## 5. Enable deep link support
> 인수 외에도 딥 링크를 사용하여 앱의 대상을 타사 앱에 노출할 수도 있습니다. \
  이 섹션에서는 이전 섹션에서 생성한 경로에 대한 새로운 딥 링크를 추가하여 앱 외부에서 이름으로 직접 개별 계정으로 딥 링크를 활성화합니다.

```
adb shell am start -d "rally://accounts/Checking" -a android.intent.action.VIEW
```


# ComposeFest2021
Navigation 코드랩 자료입니다.
본 폴더를 Android Studio를 이용해서 열어주세요.
작업을 완료하고, push 해주세요.

# Navigation in Jetpack Compose Codelab

This folder contains the source code for the
[Navigation in Jetpack Compose Codelab](https://developer.android.com/codelabs/jetpack-compose-navigation)
codelab.

## License
```
Copyright 2021 The Android Open Source Project

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
