-- 코드를 작성해주세요
select ii.ITEM_ID, ITEM_NAME, RARITY
from item_info ii left join item_tree it on ii.item_id = it.parent_item_id
where parent_item_id is null
order by ii.ITEM_ID desc;
