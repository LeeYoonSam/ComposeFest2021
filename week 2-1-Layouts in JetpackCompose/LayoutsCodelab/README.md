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

## Material Components
> Compose에는 앱을 만드는 데 사용할 수 있는 기본 제공 Material Component 컴포저블이 함께 제공됩니다. 가장 높은 수준의 컴포저블은 Scaffold입니다.

- `Scaffold` 및 `TopAppBar`는 Material 애플리케이션을 갖는 데 사용할 수 있는 몇 가지 구성 요소일 뿐입니다. `BottomNavigation` 또는 `BottomDrawer`와 같은 다른 머티리얼 구성 요소에 대해서도 동일한 작업을 수행할 수 있습니다. 연습으로 지금까지 했던 것과 같은 방식으로 해당 API로 스캐폴드 슬롯을 채우도록 초대합니다.

### Scaffold
- `Scaffold`를 사용하면 기본 Material Design 레이아웃 구조로 UI를 구현할 수 있습니다. `TopAppBar`, `BottomAppBar`, `FloatingActionButton` 및 `Drawer`와 같은 가장 일반적인 최상위 Material 구성 요소에 대한 슬롯을 제공합니다.
- `Scaffold`에는 `@Composable () -> Unit` 유형의 `topBar` 매개변수가 있는 상단 `AppBar`용 슬롯이 있습니다. 즉, 원하는 구성 가능으로 슬롯을 채울 수 있습니다.


### [More icons](https://fonts.google.com/icons?selected=Material+Icons)
- 프로젝트에 새 종속성을 추가하여 전체 머티리얼 아이콘 목록을 사용할 수 있습니다.

```gradle
dependencies {
  ...
  implementation "androidx.compose.material:material-icons-extended:$compose_version"
}
```

## Create your custom layout
> Compose는 Column, Row 또는 Box와 같은 기본 제공 컴포저블을 결합하여 일부 사용자 지정 레이아웃에 충분할 수 있는 작은 청크로 컴포저블의 재사용성을 촉진합니다.\
  그러나 수동으로 자식뷰를 측정하고 배치해야 하는 앱 고유의 것을 빌드해야 할 수도 있습니다. 이를 위해 Layout 컴포저블을 사용할 수 있습니다. 실제로 Column 및 Row와 같은 모든 상위 레벨 레이아웃은 Layout 으로 빌드됩니다.

- View 시스템에서 custom layout을 생성하려면 ViewGroup을 확장하고 측정 및 레이아웃 기능을 구현해야 했습니다. Compose에서는 레이아웃 구성 가능을 사용하여 함수를 작성하기만 하면 됩니다.

### Compose의 레이아웃 원칙
- 일부 composable 함수는 호출될 때 화면에 렌더링될 UI 트리에 추가되는 UI 조각을 내보냅니다.
- 각 방출(또는 요소)에는 하나의 부모와 잠재적으로 많은 자식이 있습니다. 또한 부모 내 위치((x, y) 위치 및 크기: 너비 및 높이)가 있습니다.
- 요소는 충족되어야 하는 Constraints로 스스로를 측정해야 합니다.
- 제약 조건은 요소의 최소 및 최대 너비와 높이를 제한합니다. 요소에 자식 요소가 있는 경우 자체 크기를 결정하는 데 도움이 되도록 각 요소를 측정할 수 있습니다. \
- 요소가 자신의 크기를 보고하면 자신과 관련된 자식 요소를 배치할 수 있습니다. 이것은 사용자 정의 레이아웃을 만들 때 추가로 설명됩니다.


**Compose UI는 다중 패스 측정을 허용하지 않습니다.**
- 이는 레이아웃 요소가 다른 측정 구성을 시도하기 위해 자식을 두 번 이상 측정하지 않을 수 있음을 의미합니다. 단일 패스 측정은 성능에 좋으며 Compose가 깊은 UI 트리를 효율적으로 처리할 수 있습니다.
- 레이아웃 요소가 자식을 두 번 측정하고 해당 자식이 자식 중 하나를 두 번 측정하는 식이라면 전체 UI를 레이아웃하려는 한 번의 시도는 많은 작업을 수행해야 하므로 앱 성능을 계속 유지하기 어렵습니다.
- 그러나 한번만 측정해서 알려 주는 것 외에 추가 정보가 정말로 필요한 경우가 있습니다.

### Using the layout modifier
> 레이아웃 수정자를 사용하여 요소를 측정하고 배치하는 방법을 수동으로 제어합니다. 일반적으로 사용자 지정 레이아웃 수정자의 일반적인 구조는 다음과 같습니다.

```kotlin
fun Modifier.customLayoutModifier(...) = Modifier.layout { measurable, constraints ->
  ...
})
```

**`레이아웃` Modifier를 사용할 때 두 개의 람다 매개변수를 얻습니다.**
- `measurable`: 측정 및 배치할 자식
- `constraints`: 자식의 너비와 높이에 대한 최소 및 최대
- `placeRelative`는 현재 `layoutDirection`을 기반으로 배치 가능한 위치를 자동으로 조정합니다.

## Layout modifiers under the hood
### Analysing a modifier
> Modifier 및 LayoutModifier는 공용 인터페이스이므로 고유한 modifier를 만들 수 있습니다.