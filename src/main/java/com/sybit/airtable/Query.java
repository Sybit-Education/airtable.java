package com.sybit.airtable;/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */

import java.util.List;

public interface Query {

    Integer getMaxRecords();

    String getView();

    List<Sort> getSort();
}
