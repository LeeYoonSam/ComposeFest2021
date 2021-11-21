# ComposeFest2021
Using state in Jetpack Compose 본 폴더를 Android Studio를 이용해서 열어주세요.
작업을 완료하고, push 해주세요.

1. 영상 보러가기

    [![Video Label](https://img.youtube.com/vi/XXKmlKolcPk/0.jpg)](https://youtu.be/XXKmlKolcPk)

2. 슬라이드 자료 보러가기 👉 [링크](https://speakerdeck.com/veronikapj/2021-composefest2021-using-state-in-jetpack-compose)


<br/><br/>
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


### 5. Memory in Compose

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

### 6. State in Compose

**stateful composable**
- `stateful composable`은 시간이 지남에 따라 변경될 수 있는 상태를 소유하는 컴포저블입니다.

**mutableStateOf를**
- `mutableStateOf`를 저장하여 관찰 가능한 상태 홀더를 제공하는 Compose의 내장 유형인 MutableState<String>을 생성합니다.

```kotlin
interface MutableState<T> : State<T> {
    override var value: T
}
```

**mutableStateOf 또는 getValue가 정의되지 않았다는 컴파일러 오류가 발생하면 다음 가져오기가 있는지 확인하십시오.**

```
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
```

**MutableState 개체를 선언하는 세 가지 방법**
```
val state = remember { mutableStateOf(default) }
var value by remember { mutableStateOf(default) }
val (value, setValue) = remember { mutableStateOf(default) }
```

- 컴포지션에서 State<T>(또는 기타 상태 저장 개체)를 만들 때 이를 기억하는 것이 중요합니다. 그렇지 않으면 모든 구성이 다시 초기화됩니다.
- MutableState<T>는 MutableLiveData<T>와 유사하지만 런타임에 통합됩니다. 관찰 가능하므로 업데이트될 때마다 compose에 알리고 compose는 이를 읽는 모든 구성 요소를 재구성할 수 있습니다.

### 7. Dynamic UI based on state

- `Recomposition`은 새로운 데이터를 기반으로 `composition 트리`의 구조를 변경할 수 있습니다.
- 소프트웨어 키보드를 제어하기 위해 `LocalSoftwareKeyboardController.current`를 사용합니다. 이것은 실험적인 API이기 때문에 `@OptIn(ExperimentalComposeUiApi::class)`로 함수에 주석을 달아야 합니다.

### 8. Extracting stateless composables
- `stateless composables`으로 변환
- `stateless composables`에는 모든 UI 관련 코드가 있고 `stateful composable`에는 UI 관련 코드가 없습니다. 이렇게 하면 상태를 다르게 지원하려는 상황에서 UI 코드를 재사용할 수 있습니다.
- `Stateful 컴포저블`에서 `Stateless 컴포저블`을 추출하면 다른 위치에서 UI를 더 쉽게 재사용할 수 있습니다.


### 9. Use State in ViewModel

- 상태를 올릴 때 어디로 가야 하는지 파악하는 데 도움이 되는 세 가지 규칙이 있습니다.
    1. 상태는 상태(또는 읽기)를 사용하는 모든 컴포저블 중 최소한 가장 낮은 공통 부모로 호이스트되어야 합니다.
    2. 상태는 변경(또는 수정)될 수 있는 최소한 가장 높은 수준으로 호이스트되어야 합니다.
    3. 동일한 이벤트에 대한 응답으로 두 상태가 변경되면 함께 호이스트되어야 합니다. 이러한 규칙이 요구하는 것보다 더 높은 수준으로 상태를 호이스트할 수 있지만 상태를 덜 호이스팅하면 단방향 데이터 흐름을 따르기가 어렵거나 불가능합니다.

**`mutableStateListOf`를 사용하도록 ViewModel 변환**
- `ViewModel`에서 `mutableStateListOf`를 사용하여 탐색하고 Compose를 대상으로 할 때 `LiveData<List>`와 비교하여 상태 코드를 단순화하는 방법
- `mutableStateListOf`를 사용하면 관찰 가능한 `MutableList`의 인스턴스를 만들 수 있습니다. 이것은 `MutableList`로 작업하는 것과 같은 방식
- `private set`을 지정함으로써 이 상태 객체에 대한 쓰기를 `ViewModel` 내부에서만 볼 수 있는 `private setter`로 제한하고 있습니다.
- 주의: `mutableStateListOf` 및 `MutableState`로 수행된 작업은 `Compose`를 위한 것입니다. 이 `ViewModel`이 `View` 시스템에서도 사용되었다면 `LiveData`를 계속 사용하는 것이 좋습니다.
- `State<T>`는 `Compose`에서 사용하기 위한 것입니다. `Compose` 외부에서 사용되는 애플리케이션 상태는 `State<T>`를 사용하여 상태를 유지해서는 안 됩니다.

### 10. Test State in ViewModel
> 애플리케이션 로직이 올바른지 확인하기 위해 ViewModel을 테스트하는 것이 좋습니다. 이 섹션에서는 상태에 대해 `State<T>`를 사용하여 뷰 모델을 테스트하는 방법을 보여주는 테스트를 작성할 것입니다.

### 11. Reuse stateless composables
> 컴포저블에 여러 인수가 있는 경우 명명된 인수를 사용하는 것이 좋습니다.

- `Stateless 컴포저블`은 상태가 저장되는 방식과 분리됩니다.
- 이 예제에서는 ViewModel에 있는 목록의 요소로 TodoItemInlineEditor에 전달된 상태를 유지하고 있습니다. 그러나 TodoItemInlineEditor의 코드를 변경하지 않고도 Room 데이터베이스에 저장되도록 쉽게 변경할 수 있습니다.
- `LazyColumn`은 현재 화면에 있는 항목만 구성하고 떠나는 즉시 폐기합니다. `RecyclerView`와 달리 재활용을 수행할 필요가 없습니다. `compose`는 보다 효율적인 방식으로 새 컴포저블 생성을 처리합니다.

### 12. Use slots to pass sections of the screen

**Scaffold**
- `Scaffold`는 화면의 `topBar`, `bottomBar`, `body`와 같은 `Material 디자인`의 전체 화면을 설명하기 위한 구성 요소입니다.
- 슬롯은 호출자가 화면의 섹션을 설명할 수 있도록 하는 구성 가능한 함수에 대한 매개변수입니다. @Composable() -> Unit 유형의 매개변수를 사용하여 슬롯을 선언하십시오.


### 참고
[JetNews](https://github.com/android/compose-samples/)
- JetNews는 상태 비저장 컴포저블을 사용하여 구축된 화면에서 상태를 관리하기 위해 상태 저장 컴포저블을 사용하기 위해 단방향 데이터 흐름을 사용하는 방법을 보여줍니다.

**Reference docs**
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel?authuser=4)
- [remember](https://developer.android.com/reference/kotlin/androidx/compose/runtime/package-summary.html?authuser=4#remember(kotlin.Function0))
- [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State?authuser=4)
- [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState?authuser=4)


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
