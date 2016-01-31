package trikita.anvil.recycle.example;

import static trikita.anvil.DSL.text;
import static trikita.anvil.DSL.textView;

import trikita.anvil.RenderableView;
import trikita.anvil.recyclerview.Recycler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewExample extends Activity {
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(new SimpleList(this));
	}

	private static List<String> items() {
		List<String> items = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			items.add("Item #" + i);
		}
		return items;
	}

	public static class SimpleList extends RenderableView {
		private final List<String> mItems = items();

		public SimpleList(Context c) {
			super(c);
		}

		public void view() {
			Recycler.view(() -> {
				Recycler.layoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
				Recycler.hasFixedSize(true);
				Recycler.itemAnimator(new DefaultItemAnimator());
				Recycler.adapter(Recycler.Adapter.simple(mItems, (vh) -> {
					textView(() -> {
						text(mItems.get(vh.getAdapterPosition()));
					});
				}));
			});
		}
	}
}

