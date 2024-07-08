select it1.ITEM_ID, ifo.ITEM_NAME, ifo.RARITY
from item_tree it1
    left join item_tree it2 on it1.item_id = it2.parent_item_id
    left join item_info ifo on it1.item_id = ifo.item_id
where it2.parent_item_id is null
order by it1.item_id desc;