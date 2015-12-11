package trikita.anvil.recycle.example;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import trikita.anvil.RenderableView;
import trikita.anvil.recyclerview.Recycler;

import static trikita.anvil.DSL.*;

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
		private List<String> mItems = items();

		public SimpleList(Context c) {
			super(c);
		}

		public void view() {
			Recycler.view(() -> {
				Recycler.layoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
				Recycler.hasFixedSize(true);
				Recycler.adapter(Recycler.Adapter.simple(mItems, (vh) -> {
					textView(() -> {
						text(mItems.get(vh.getAdapterPosition()));
					});
				}));
			});
		}
	}
}

