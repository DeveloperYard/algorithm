select e1.id, count(e2.id) as CHILD_COUNT
from ECOLI_DATA e1 left join ECOLI_DATA e2 on e2.parent_id = e1.id
group by e1.id
order by e1.id;