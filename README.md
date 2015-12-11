# anvil-recyclerview

Simple RecyclerView bindings for Anvil.

The library provides a `Recycler` "namespace" which contains utility functions
for creating RecyclerViews, modifying their properties and binding adapters.

## Gradle

``` gradle
repositories {
	maven { url = 'https://jitpack.io/' }
}
dependencies {
	compile 'co.trikita:anvil-sdk15:+' // or sdk19
	compile 'com.github.trikita:anvil-recyclerview:1.1'
}
```

## Usage

Recycler.view(...) creates a new View and configures it using Anvil DSL.

Recycler.Adapter is an implementation of RecyclerView.Adapter which behaves in
a reactive manner, e.g. on Anvil.render() it updates on the the view holders
that have been changed.

Recycler.Adapter.simple(list, renderable) is a syntax sugar for binding simple
adapter using a plain List as data model.

``` java
List<Item> mItems = ...;

public void view() {

	Recycler.view(() -> {

		Recycler.layoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
		Recycler.hasFixedSize(true);

		Recycler.adapter(Recycler.Adapter.simple(mItems, (viewHolder) -> {
			textView(() -> {
				text(mItems.get(viewHolder.getAdapterPosition()).getName());
			});
		}));
	});
}
```

## Further improvements

It might be useful to add some support for the most common gestures like drag
to reorder items or swipe to dismiss.

It might be also helpful to add multiselect support.

Feel free to submit your pull requests!
