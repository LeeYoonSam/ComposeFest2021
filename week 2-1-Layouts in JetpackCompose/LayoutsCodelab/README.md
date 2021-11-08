# [Layouts Codelab](https://developer.android.com/codelabs/jetpack-compose-layouts?authuser=4&continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fcompose%3Fhl%3Den%26authuser%3D4%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fjetpack-compose-layouts&hl=en#0)
- 머티리얼 컴포넌트 컴포저블 사용 방법
- 수정자는 무엇이며 레이아웃에서 어떻게 사용할 수 있습니까?
- 사용자 정의 레이아웃을 만드는 방법
- 내장 기능이 필요할 때

## Modifiers
> Modifiers를 사용하면 컴포저블을 꾸밀 수 있습니다.\
  동작, 모양을 변경하고, 접근성 레이블과 같은 정보를 추가하고, 사용자 입력을 처리하거나, 클릭, 스크롤, 드래그 또는 확대/축소를 만드는 것과 같은 고급 상호 작용을 추가할 수도 있습니다.\
  Modifiers는 일반 Kotlin 객체입니다. 변수에 할당하고 재사용할 수 있습니다. 여러 Modifiers를 차례로 연결하여 구성할 수도 있습니다.

- 순서가 중요하므로 modifiers를 연결할 때 주의하십시오. 단일 인수로 연결되므로 순서가 최종 결과에 영향을 줍니다.

## Slot APIs
> Compose는 UI를 빌드하는 데 사용할 수 있는 고급 Material Components 컴포저블을 제공합니다. UI 생성을 위한 빌딩 블록이므로 화면에 표시할 정보를 제공해야 합니다.\
  Slot APIs는 컴포저블(이 사용 사례에서는 사용 가능한 Material Components 컴포저블) 위에 사용자 정의 레이어를 가져오기 위해 Compose가 도입한 패턴입니다.

- 자식 구성 가능한 람다(content: @Composable () -> Unit)를 취하는 Button용 API를 제공합니다. 이를 통해 Button 내에서 방출될 자신만의 컴포저블을 정의할 수 있습니다.

```kotlin
Button {
    Row {
        MyImage()
        Spacer(4.dp)
        Text("Button")
    }
}

@Composable
fun Button(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    ...
    content: @Composable () -> Unit
)
```