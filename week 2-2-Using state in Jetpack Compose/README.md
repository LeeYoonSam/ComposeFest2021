# ComposeFest2021
Using state in Jetpack Compose 본 폴더를 Android Studio를 이용해서 열어주세요.
작업을 완료하고, push 해주세요.

# Using State in Jetpack Compose Codelab

This folder contains the source code for the [Using State in Jetpack Compose codelab](https://developer.android.com/codelabs/jetpack-compose-state).


In this codelab, you will explore patterns for working with state in a declarative world by building a Todo application. We'll see what unidirectional
data flow is, and how to apply it in a Jetpack Compose application to build stateless and stateful composables.

## 배울것
- 단방향 데이터 흐름이란
- UI에서 상태 및 이벤트에 대해 생각하는 방법
- Compose에서 Architecture Component의 ViewModel 및 LiveData를 사용하여 상태를 관리하는 방법
- Compose가 상태를 사용하여 화면을 그리는 방법
- 상태를 호출자로 이동해야 하는 경우
- Compose에서 내부 상태를 사용하는 방법
- State<T>를 사용하여 Compose와 상태를 통합하는 방법

### The UI update loop
- `Event` – 사용자 또는 프로그램의 다른 부분에 의해 이벤트가 생성됩니다. 
- `Update State` – 이벤트 핸들러는 UI에서 사용하는 상태를 변경합니다.
- `Display State` – 새 상태를 표시하도록 UI가 업데이트되었습니다.

`Compose`에서 상태를 관리하는 것은 상태와 이벤트가 서로 상호 작용하는 방식을 이해하는 것입니다.

- Observable은 누구나 해당 상태의 변경 사항을 수신할 수 있는 방법을 제공하는 상태 개체입니다.
- `LiveData`, `StateFlow`, `Flow` 및 `Observable`은 모두 관찰 가능합니다.

### 단방향 데이터 흐름
> 단방향 데이터 흐름은 상태가 아래로 흐르고 이벤트가 위로 흐르는 디자인입니다. 이러한 방식으로 코드를 구성하면 몇 가지 이점을 얻을 수 있습니다.

- 테스트 가능성 – 상태를 표시하는 UI에서 상태를 분리하여 ViewModel과 Activity를 모두 테스트하는 것이 더 쉽습니다.
- 상태 캡슐화 – 상태는 한 곳(ViewModel)에서만 업데이트할 수 있으므로 UI가 커짐에 따라 부분 상태 업데이트 버그가 발생할 가능성이 적습니다.
- UI 일관성 – 관찰 가능한 상태 홀더를 사용하여 모든 상태 업데이트가 UI에 즉시 반영됩니다.

### State hoisting
> State hoisting은 구성 요소를 상태 비저장 상태로 만들기 위해 상태를 위로 이동하는 패턴입니다. 컴포저블에 적용할 때 이는 종종 컴포저블에 두 개의 매개변수를 도입하는 것을 의미합니다.

- value: T – 표시할 현재 값
- onValueChange: (T) -> Unit – 값 변경을 요청하는 이벤트, 여기서 T는 제안된 새 값입니다.

### Surface
> Surface는 앱에 배경을 추가하고 텍스트 색상을 구성합니다.

- [Compose의 테마 설정](https://developer.android.com/jetpack/compose/themes?authuser=4)

### [High-order functions and lambdas](https://kotlinlang.org/docs/lambdas.html)
- Composable 에서 코틀린 람다 구문 사용 예

```kotlin
@Composable
private fun TodoActivityScreen(todoViewModel: TodoViewModel) {
   val items = listOf<TodoItem>()
   TodoScreen(
       items = items,
       onAddItem = { todoViewModel.addItem(it) },
       onRemoveItem = { todoViewModel.removeItem(it) }
   )
}
```

### Pass the state down
- 단방향 데이터 흐름의 이벤트를 연결한 후 상태를 전달해야 합니다.
- observeAsState는 LiveData를 관찰하고 LiveData가 변경될 때마다 업데이트되는 State 객체를 반환합니다.
- 컴포저블이 컴포지션에서 제거되면 자동으로 관찰을 중지합니다.


### Memory in Compose

**stateful composable**
- stateful composable은 시간이 지남에 따라 변경될 수 있는 상태를 유지하는 컴포저블입니다.

**Exploring recomposition**
- 재구성은 데이터가 변경될 때 트리를 업데이트하기 위해 동일한 컴포저블을 다시 실행하는 프로세스입니다.
- 재구성은 작성 트리를 업데이트하기 위해 새 입력으로 다시 composable을 호출하는 프로세스입니다. \
  이 경우 새 목록으로 TodoScreen을 다시 호출하면 LazyColumn이 화면의 모든 자식을 재구성합니다. 그러면 TodoRow가 다시 호출되어 새로운 임의의 색조를 생성합니다.

**remember**
- remember는 composable 함수 메모리를 제공합니다.
- A remember call has two parts:
    - key arguments – 이 메모리가 사용하는 "키"는 괄호 안에 전달되는 부분입니다. 여기서 todo.id를 키로 전달합니다.
    - calculation – remember할 새 값을 계산하는 람다로, 후행 람다로 전달됩니다. 여기에서 randomTint()를 사용하여 임의의 값을 계산합니다.

```kotlin
remember(todo.id) { randomTint() }
```

## Screenshots

![Finished code](screenshots/state_movie.gif "After: Animation of fully completed project")

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
