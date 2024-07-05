select f1.ID, f2.FISH_NAME, mxLen as LENGTH
from (
    select fish_type, max(length) as mxLen
    from fish_info f1
    group by fish_type
) as mx_len
left join fish_info f1 on f1.length = mx_len.mxLen and f1.fish_type = mx_len.fish_type
left join fish_name_info f2 on f1.fish_type = f2.fish_type
order by f1.id asc;