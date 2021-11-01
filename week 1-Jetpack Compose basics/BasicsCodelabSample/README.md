# BasicsCodelab (1주차)

## 용어 정리

### Surface
> Surface 내부에 중첩된 구성 요소는 해당 배경색 위에 그려집니다. \
  Surface는 배경이 기본 색상으로 설정되면 그 위에 있는 모든 텍스트가 **테마에 정의된 onPrimary 색상을 사용**해야 한다는 것을 이해해야 합니다.

- Material은 대부분의 앱에 공통적인 좋은 기본값과 패턴을 제공하기 때문에 독단적이라고 합니다. \
- Compose의 Material 구성 요소는 더 많은 유연성이 필요한 경우 앱 구성 요소에서도 액세스할 수 있는 다른 기본 구성 요소(androidx.compose.foundation에 있음) 위에 구축됩니다.

```kotlin
Surface(color = MaterialTheme.colors.primary) {
    Text (text = "Hello $name!")
}
```

### Modifier
> Surface 및 Text와 같은 대부분의 Compose UI 요소는 선택적 Modifier 매개변수를 허용합니다. Modifier는 UI 요소에 상위 레이아웃 내에서 **레이아웃, 표시 또는 동작하는 방법**을 알려줍니다.

- 예를 들어 패딩 Modifier는 요소 주위에 일정한 공간을 적용합니다. Modifier.padding()을 사용하여 패딩 Modifier를 만들 수 있습니다.

```kotlin
Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
```

- `padding` : 해당 뷰에 padding 을 적용
- `fillMaxWidth` : 해당 뷰의 width 를 꽉 채움

### Reusing composables
> UI에 더 많은 구성 요소를 추가할수록 더 많은 수준의 중첩이 생성됩니다. \
  함수가 정말 커지면 가독성에 영향을 줄 수 있습니다. \
  재사용 가능한 작은 구성 요소를 만들어 앱에서 사용되는 UI 요소 라이브러리를 쉽게 구축할 수 있습니다. \
  각각은 화면의 작은 부분을 담당하며 독립적으로 편집할 수 있습니다.

### Compose의 세 가지 기본 표준 레이아웃 요소
- `Column` : 내부의 각 자식을 세로로 배치
- `Row` : 내부의 각 자식을 가로로 배치
- `Box` : 내부의 자식은 서로 겹쳐집니다. 정렬 Modifier를 사용하여 컴포저블을 그려야 하는 위치를 지정할 수 있습니다.

### mutableStateOf
> 컴포저블에 내부 상태를 추가하려면 mutableStateOf 함수를 사용하면 Compose가 해당 상태를 읽는 함수를 재구성할 수 있습니다.

- `State` 및 `MutableState`는 일부 값을 보유하고 해당 값이 변경될 때마다 UI 업데이트(재구성)를 트리거하는 인터페이스입니다.
- 재구성에서 상태를 보존하려면 `remember`를 사용하여 변경 가능한 상태를 기억하십시오.


## 참고
[Jetpack Compose basics](https://developer.android.com/codelabs/jetpack-compose-basics?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fcompose%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fjetpack-compose-basics#0)
[Box](https://foso.github.io/Jetpack-Compose-Playground/layout/box/)