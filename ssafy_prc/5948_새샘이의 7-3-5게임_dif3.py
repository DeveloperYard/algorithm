def sol(num_arr):
    ans = set()
    l = len(num_arr)
    for i in range(l):
        for j in range(i+1, l):
            for k in range(j+1, l):
                ans.add(num_arr[i]+num_arr[j]+num_arr[k])

    ans = list(ans)
    ans.sort()
    return ans[-5]


for tc in range(int(input())):
    num_arr = list(map(int, input().split()))
    res = sol(num_arr)
    print(f'#{tc+1} {res}')