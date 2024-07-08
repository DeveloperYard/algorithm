select ed1.ID, ifnull(count(ed2.parent_id), 0) as CHILD_COUNT
from ecoli_data ed1
    left join ecoli_data ed2 on ed1.id = ed2.parent_id -- ed2 -> child!
group by ed1.id
order by ed1.ID asc;