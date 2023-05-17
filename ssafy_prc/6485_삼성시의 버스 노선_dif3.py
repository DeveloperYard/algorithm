def sol(paths, stations):
    ans = []
    path_arr = [0]*5000
    for path in paths:
        a, b = path
        for i in range(a, b+1):
            path_arr[i-1] += 1

    for i in stations:
        ans.append(path_arr[i-1])

    return ans



for tc in range(int(input())):
    path_n = int(input())
    path = []
    for i in range(path_n):
        a, b = map(int, input().split())
        path.append([a, b])

    station_n = int(input())
    stations = []
    for i in range(station_n):
        stations.append(int(input()))

    res = sol(path, stations)
    print(f'#{tc+1}', end=" ")
    for i in range(len(res)):
        print(str(res[i]), end=" ")
    print()

